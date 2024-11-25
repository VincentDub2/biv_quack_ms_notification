package fr.polytech.service_notification.controller;

import fr.polytech.service_notification.model.Notification;
import fr.polytech.service_notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @GetMapping
    public List<Notification> getAllNotifications() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/recipient/{recipientId}")
    public Notification getNotificationByRecipientId(@PathVariable Long recipientId) {
        return service.findByRecipientId(recipientId);
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return service.save(notification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        service.delete(id);
    }
}
