package fr.polytech.service_notification.repository;

import fr.polytech.service_notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    /**
     * Find a notification by its recipient ID.
     * @param recipientId The recipient ID.
     * @return The found notification.
     */
    Notification findByRecipientId(Long recipientId);

}
