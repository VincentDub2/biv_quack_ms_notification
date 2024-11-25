package fr.polytech.service_notification.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.service_notification.dto.EvaluationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "evaluation-notifications", groupId = "notification-group")
    public void listen(String message) {
        try {
            EvaluationEvent event = objectMapper.readValue(message, EvaluationEvent.class);
            System.out.println("Notification enrichie reçue : " + event.getMessage());

            // Créer et sauvegarder une notification avec les données enrichies...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
