package fr.polytech.service_notification.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.service_notification.clients.EmplacementClient;
import fr.polytech.service_notification.clients.UserClient;
import fr.polytech.service_notification.dto.EmplacementResponse;
import fr.polytech.service_notification.dto.EvaluationEvent;
import fr.polytech.service_notification.dto.ReservationEvent;
import fr.polytech.service_notification.dto.UserResponse;
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
            System.out.println("Notification enrichie reçue : " + event.getMessage());

            Notification notification = new Notification();
            notification.setRecipient(event.getUserId());
            notification.setMessage(event.getMessage());
            notification.setRead(false);

            notificationService.save(notification);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "reservation-notifications", groupId = "notification-group")
    public void listenReservationNotifications(String message) {
        try {
            ReservationEvent event = objectMapper.readValue(message, ReservationEvent.class);
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


}
