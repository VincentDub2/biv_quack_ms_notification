package fr.polytech.service_notification.dto;

import java.util.List;

public class EmplacementResponse {

    private Long idEmplacement;
    private Long idHote;
    private String nom;
    private String adresse;
    private String description;
    private List<String> commodites;
    private String image;
    private Double latitude;
    private Double longitude;
    private Double prixParNuit;
    private String dateDebut;
    private String dateFin;

    // Constructeurs
    public EmplacementResponse() {}

    public EmplacementResponse(Long idEmplacement, Long idHote, String nom, String adresse, String description,
                               List<String> commodites, String image, Double latitude, Double longitude,
                               Double prixParNuit, String dateDebut, String dateFin) {
        this.idEmplacement = idEmplacement;
        this.idHote = idHote;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
        this.commodites = commodites;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.prixParNuit = prixParNuit;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    // Getters et Setters
    public Long getIdEmplacement() {
        return idEmplacement;
    }

    public void setIdEmplacement(Long idEmplacement) {
        this.idEmplacement = idEmplacement;
    }

    public Long getIdHote() {
        return idHote;
    }

    public void setIdHote(Long idHote) {
        this.idHote = idHote;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCommodites() {
        return commodites;
    }

    public void setCommodites(List<String> commodites) {
        this.commodites = commodites;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getPrixParNuit() {
        return prixParNuit;
    }

    public void setPrixParNuit(Double prixParNuit) {
        this.prixParNuit = prixParNuit;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
}
