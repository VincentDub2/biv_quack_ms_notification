package fr.polytech.service_notification.repository;

import fr.polytech.service_notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    /**
     * Trouve toutes les notifications pour un utilisateur donné.
     * @param recipientId L'ID du destinataire.
     * @return Une liste de notifications.
     */
    List<Notification> findByRecipientId(Long recipientId);




}
