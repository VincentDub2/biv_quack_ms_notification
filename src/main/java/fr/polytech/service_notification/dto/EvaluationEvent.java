package fr.polytech.service_notification.dto;

public class EvaluationEvent {
    private String evaluationType;
    private Long userId;
    private String target;
    private String message;

    // Getters et Setters
    public String getEvaluationType() { return evaluationType; }
    public void setEvaluationType(String evaluationType) { this.evaluationType = evaluationType; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getTarget() { return target; }
    public void setTarget(String target) { this.target = target; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
