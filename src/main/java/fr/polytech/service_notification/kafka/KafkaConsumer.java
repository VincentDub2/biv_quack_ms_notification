package fr.polytech.service_notification.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.service_notification.clients.EmplacementClient;
import fr.polytech.service_notification.clients.UserClient;
import fr.polytech.service_notification.dto.*;
import fr.polytech.service_notification.model.Notification;
import fr.polytech.service_notification.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    NotificationService notificationService;

    @Autowired
    private UserClient userClient;

    @Autowired
    private EmplacementClient emplacementClient;

    @KafkaListener(topics = "evaluation-notifications", groupId = "notification-group")
    public void listenEvaluationNotifications(String message) {
        try {
            EvaluationEvent event = objectMapper.readValue(message, EvaluationEvent.class);
            UserResponse voyageur = userClient.getUserById(event.getVoyageurId());
            LOGGER.info("Notification reçue : {}", event.toString());
            EmplacementResponse emplacement = emplacementClient.getEmplacementById(event.getEmplacementId());

            Notification evaluationNotification = new Notification();
            evaluationNotification.setRecipient(emplacement.getIdHote());
            evaluationNotification.setMessage(
                    "Nouvelle évaluation de " + voyageur.getUsername() + " pour votre emplacement " + emplacement.getNom() + " : " + event.getNote() + "/5"
            );
            evaluationNotification.setRead(false);



            notificationService.save(evaluationNotification);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "reservation-notifications", groupId = "notification-group")
    public void listenReservationNotifications(String message) {
        try {
            ReservationEvent event = objectMapper.readValue(message, ReservationEvent.class);
            LOGGER.info("Notification reçue : {}", event.toString());
            if (event.getVoyageurId() == null) {
                LOGGER.info("Id du voyageur nul: {}", event.getEmplacementId());
                return;

            }
            UserResponse voyeur = userClient.getUserById(event.getVoyageurId());
            EmplacementResponse emplacement = emplacementClient.getEmplacementById(event.getEmplacementId());
            UserResponse hote = userClient.getUserById(emplacement.getIdHote());


            LOGGER.info("Notification enrichie reçue : {}", event.getEmplacementId());



            if (event.getError()) {

                Notification notification = new Notification();
                notification.setRecipient(event.getVoyageurId());
                notification.setMessage(
                        "Erreur lors de la réservation de " + emplacement.getNom() + "du " +event.getDateArrive() + " au " + event.getDateDepart()
                );
                notification.setRead(false);

                notificationService.save(notification);

            } else if (event.getIsCancelled()) {
                Notification notificationVoyageur = new Notification();
                notificationVoyageur.setRecipient(event.getVoyageurId());
                notificationVoyageur.setMessage(
                        "Réservation de " + emplacement.getNom() + " annulée" + "du " +event.getDateArrive() + " au " + event.getDateDepart()
                );
                notificationVoyageur.setRead(false);

                notificationService.save(notificationVoyageur);

                Notification notificationHote = new Notification();
                notificationHote.setRecipient(emplacement.getIdHote());
                notificationHote.setMessage(
                      voyeur.getUsername() + " a annulé sa réservation de " + emplacement.getNom() + "du " +event.getDateArrive() + " au " + event.getDateDepart()
                );
            }else {
                Notification notificationVoyageur = new Notification();
                notificationVoyageur.setRecipient(event.getVoyageurId());
                notificationVoyageur.setMessage(
                        "Réservation de " + emplacement.getNom() + " confirmée" + "du " +event.getDateArrive() + " au " + event.getDateDepart()
                );
                notificationVoyageur.setRead(false);

                notificationService.save(notificationVoyageur);

                Notification notificationHote = new Notification();
                notificationHote.setRecipient(emplacement.getIdHote());
                notificationHote.setMessage(
                        voyeur.getUsername() + " a réservé votre emplacement " + emplacement.getNom() + "du " +event.getDateArrive() + " au " + event.getDateDepart()
                );
                notificationHote.setRead(false);

                notificationService.save(notificationHote);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "message-notifications", groupId = "notification-group")
    public void listenMessageNotifications(String message) {
        try {
            MessageEvent event = objectMapper.readValue(message, MessageEvent.class);
            LOGGER.info("Notification reçue : {}", event.toString());
            UserResponse sender = userClient.getUserById(event.getSenderId());
            UserResponse receiver = userClient.getUserById(event.getReceiverId());

            Notification messageNotification = new Notification();
            messageNotification.setRecipient(event.getReceiverId());
            messageNotification.setMessage(
                    "Nouveau message de " + sender.getUsername()

            );
            messageNotification.setRead(false);

            notificationService.save(messageNotification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
