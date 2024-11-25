package fr.polytech.service_notification.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.service_notification.dto.EvaluationEvent;
import fr.polytech.service_notification.model.Notification;
import fr.polytech.service_notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    NotificationService notificationService;

    @KafkaListener(topics = "evaluation-notifications", groupId = "notification-group")
    public void listen(String message) {
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
}
