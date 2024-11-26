package fr.polytech.service_notification.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.service_notification.clients.UserClient;
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
            UserResponse user = userClient.getUserById(event.getVoyageurId());

            LOGGER.info("Notification enrichie reçue : {}", event.getEmplacementId());
            LOGGER.info("User : {}", user);



            if (event.getError()) {
                Notification notification = new Notification();
                notification.setRecipient(event.getVoyageurId());
                notification.setMessage(
                        "Erreur lors de la réservation de " + event.getEmplacementId() + " : "
                );
                notification.setRead(false);

                notificationService.save(notification);

            } else if (event.getIsCancelled()) {
                Notification notification = new Notification();
                notification.setRecipient(event.getVoyageurId());
                notification.setMessage(
                        "Réservation de " + event.getEmplacementId() + " annulée"
                );
                notification.setRead(false);

                notificationService.save(notification);
            }else {
                Notification notification = new Notification();
                notification.setRecipient(event.getVoyageurId());
                notification.setMessage(
                        "Réservation de " + event.getEmplacementId() + " effectuée"
                );
                notification.setRead(false);

                notificationService.save(notification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
