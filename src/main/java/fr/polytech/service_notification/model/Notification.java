package fr.polytech.service_notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Long recipientId;
    private boolean read;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Long getRecipient() { return recipientId; }
    public void setRecipient(Long recipient) { this.recipientId = recipient; }

    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }
}
