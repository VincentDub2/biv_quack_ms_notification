package fr.polytech.service_notification.dto;

public class EvaluationEvent {

    private Long id;
    private Long hoteId;
    private Long voyageurId;
    private Long reservationId;
    private Long emplacementId;
    private int note;
    private String commentaire;

    // Constructeur par défaut
    public EvaluationEvent() {}

    // Constructeur avec tous les champs
    public EvaluationEvent(Long id, Long hoteId, Long voyageurId, Long reservationId, Long emplacementId, int note, String commentaire) {
        this.id = id;
        this.hoteId = hoteId;
        this.voyageurId = voyageurId;
        this.reservationId = reservationId;
        this.emplacementId = emplacementId;
        this.note = note;
        this.commentaire = commentaire;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHoteId() {
        return hoteId;
    }

    public void setHoteId(Long hoteId) {
        this.hoteId = hoteId;
    }

    public Long getVoyageurId() {
        return voyageurId;
    }

    public void setVoyageurId(Long voyageurId) {
        this.voyageurId = voyageurId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getEmplacementId() {
        return emplacementId;
    }

    public void setEmplacementId(Long emplacementId) {
        this.emplacementId = emplacementId;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    // toString pour un affichage pratique
    @Override
    public String toString() {
        return "EvaluationEvent{" +
                "id=" + id +
                ", hoteId=" + hoteId +
                ", voyageurId=" + voyageurId +
                ", reservationId=" + reservationId +
                ", emplacementId=" + emplacementId +
                ", note=" + note +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
