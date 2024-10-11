package com.requestapi.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PlanetsDTO {
    private String name;

    @JsonProperty("distance_light_year")
    private Double distanceLightYear;

    @JsonProperty("radius")
    private Double radius;

    @JsonProperty("mass")
    private Double mass;

    @JsonProperty("period")
    private Double orbitalPeriod;

    @JsonProperty("temperature")
    private Double temperature;
}
