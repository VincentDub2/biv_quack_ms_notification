package fr.polytech.service_notification.dto;

public class EvaluationEvent {
    private String evaluationType;
    private Long userId;
    private Long target;
    private String message;


    /**
     * Getters and Setters
     */
    public String getEvaluationType() { return evaluationType; }
    public void setEvaluationType(String evaluationType) { this.evaluationType = evaluationType; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getTarget() { return target; }
    public void setTarget(Long target) { this.target = target; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
