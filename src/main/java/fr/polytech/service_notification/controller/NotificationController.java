package fr.polytech.service_notification.controller;

import fr.polytech.service_notification.model.Notification;
import fr.polytech.service_notification.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class NotificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService service;

    /**
     * Get all notifications.
     * @return The list of all notifications.
     */
    @GetMapping
    public List<Notification> getAllNotifications() {
        return service.findAll();
    }

    /**
     * Get a notification by its ID.
     * @param id The ID of the notification.
     * @return The found notification.
     */
    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * Get a notification by its recipient ID.
     * @param recipientId The recipient ID.
     * @return The found notification.
     */
    @GetMapping("/recipient/{recipientId}")
    public List<Notification> getNotificationByRecipientId(@PathVariable Long recipientId) {
        return service.findByRecipientId(recipientId);
    }

    /**
     * Create a notification.
     * @param notification The notification to create.
     * @return The created notification.
     */
    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return service.save(notification);
    }

    /**
     * Update a notification.
     * @param id The ID of the notification to update.
     * @param notification The new notification.
     * @return The updated notification.
     */
    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        notification.setId(id);
        return service.update(notification);
    }

    /**
     * Delete a notification.
     * @param id The ID of the notification to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        service.delete(id);
    }

    /**
     * Mark a notification as read.
     * @param id The ID of the notification to mark as read.
     */
    @PutMapping("/{id}/read")
    public void markAsRead(@PathVariable Long id) {
        Notification notification = service.findById(id);
        notification.setRead(true);
        service.update(notification);
    }
}
