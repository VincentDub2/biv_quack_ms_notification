package fr.polytech.service_notification.dto;

import java.time.LocalDateTime;

public class ReservationEvent {
    private Long voyageurId;
    private Long emplacementId;
    private LocalDateTime dateArrive;
    private LocalDateTime dateDepart;
    private boolean error;
    private boolean isCancelled;

    public ReservationEvent(
            Long voyageurId,
            Long emplacementId,
            LocalDateTime dateArrive,
            LocalDateTime dateDepart,
            boolean error,
            boolean isCancelled
    ) {
        this.voyageurId = voyageurId;
        this.emplacementId = emplacementId;
        this.dateArrive = dateArrive;
        this.dateDepart = dateDepart;
        this.error = error;
        this.isCancelled = isCancelled;
    }

    /**
     * Getters and Setters
     */
    public Long getVoyageurId() { return voyageurId; }
    public void setVoyageurId(Long voyageurId) { this.voyageurId = voyageurId; }

    public Long getEmplacementId() { return emplacementId; }
    public void setEmplacementId(Long emplacementId) { this.emplacementId = emplacementId; }

    public LocalDateTime getDateArrive() { return dateArrive; }
    public void setDateArrive(LocalDateTime dateArrive) { this.dateArrive = dateArrive; }

    public LocalDateTime getDateDepart() { return dateDepart; }
    public void setDateDepart(LocalDateTime dateDepart) { this.dateDepart = dateDepart; }

    public boolean getError() { return error; }
    public void setError(boolean error) { this.error = error; }

    public boolean getIsCancelled() { return isCancelled; }
    public void setIsCancelled(boolean isCancelled) { this.isCancelled = isCancelled;}


    /**
     * Get the right date format
     * @return String
     */
    //2024-01-01T00:00:00 to 01/01/2024
    public String convertDate(String date) {
        String[] dateSplit = date.split("T");
        String[] dateSplit2 = dateSplit[0].split("-");
        return dateSplit2[2] + "/" + dateSplit2[1] + "/" + dateSplit2[0];
    }

    /** LocalDate to String
     * @param date date
     * @return String this format  01/01/2024
     */
    public String convertDate(LocalDateTime date) {
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }

    /**
     * toString
     */
    public String toString() {
        return "ReservationEvent{" +
                "voyageurId=" + voyageurId +
                ", emplacementId=" + emplacementId +
                ", dateArrive=" + dateArrive +
                ", dateDepart=" + dateDepart +
                ", error=" + error +
                ", isCancelled=" + isCancelled +
                '}';
    }
}
