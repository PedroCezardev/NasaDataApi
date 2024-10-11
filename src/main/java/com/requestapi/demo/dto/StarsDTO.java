package com.requestapi.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StarsDTO {

    private String id; // Use String se o ID for do tipo String
    private String name;

    private String constellation;

    @JsonProperty("right_ascension")
    private String rightAscension;

    private String declination;

    @JsonProperty("apparent_magnitude")
    private Double apparentMagnitude;

    @JsonProperty("absolute_magnitude")
    private Double absoluteMagnitude;

    @JsonProperty("distance_light_year")
    private Double distanceLightYear;

    @JsonProperty("spectral_class")
    private String spectralClass;

    public StarsDTO() {
        // Construtor padrão
    }
    
    public StarsDTO(String id, String name, String constellation, String rightAscension, String declination, Double apparentMagnitude, Double absoluteMagnitude, Double distanceLightYear, String spectralClass) {
        this.id = id;
        this.name = name;
        this.constellation = constellation;
        this.rightAscension = rightAscension;
        this.declination = declination;
        this.apparentMagnitude = apparentMagnitude;
        this.absoluteMagnitude = absoluteMagnitude;
        this.distanceLightYear = distanceLightYear;
        this.spectralClass = spectralClass;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getRightAscension() {
        return rightAscension;
    }

    public void setRightAscension(String rightAscension) {
        this.rightAscension = rightAscension;
    }

    public String getDeclination() {
        return declination;
    }

    public void setDeclination(String declination) {
        this.declination = declination;
    }

    public Double getApparentMagnitude() {
        return apparentMagnitude;
    }

    public void setApparentMagnitude(Double apparentMagnitude) {
        this.apparentMagnitude = apparentMagnitude;
    }

    public Double getAbsoluteMagnitude() {
        return absoluteMagnitude;
    }

    public void setAbsoluteMagnitude(Double absoluteMagnitude) {
        this.absoluteMagnitude = absoluteMagnitude;
    }

    public Double getDistanceLightYear() {
        return distanceLightYear;
    }

    public void setDistanceLightYear(Double distanceLightYear) {
        this.distanceLightYear = distanceLightYear;
    }

    public String getSpectralClass() {
        return spectralClass;
    }

    public void setSpectralClass(String spectralClass) {
        this.spectralClass = spectralClass;
    }

}
