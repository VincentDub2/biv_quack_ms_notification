package fr.polytech.service_notification.service;

import fr.polytech.service_notification.model.Notification;
import fr.polytech.service_notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    /**
     * Get all notifications.
     * @return The list of all notifications.
     */
    public List<Notification> findAll() {
        return repository.findAll();
    }

    /**
     * Create a notification.
     * @param notification The notification to create.
     * @return The created notification.
     */
    public Notification save(Notification notification) {
        return repository.save(notification);
    }

    /**
     * Get a notification by its ID.
     * @param id The ID of the notification.
     * @return The found notification.
     */
    public Notification findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Get a notification by its recipient ID.
     * @param recipientId The recipient ID.
     * @return The found notification.
     */
    public Notification findByRecipientId(Long recipientId) {
        return repository.findByRecipientId(recipientId);
    }

    /**
     * Update a notification.
     * @param notification The notification to update.
     * @return The updated notification.
     */
    public Notification update(Notification notification) {
        return repository.save(notification);
    }

    /**
     * Delete a notification by its ID.
     * @param id The ID of the notification to delete.
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
