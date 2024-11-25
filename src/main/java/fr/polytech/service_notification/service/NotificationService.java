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

    public List<Notification> findAll() {
        return repository.findAll();
    }

    public Notification save(Notification notification) {
        return repository.save(notification);
    }

    public Notification findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Notification findByRecipientId(Long recipientId) {
        return repository.findByRecipientId(recipientId);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
