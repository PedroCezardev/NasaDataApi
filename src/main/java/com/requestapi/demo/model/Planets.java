package com.requestapi.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Document(collection = "Planets") // Definindo como um documento MongoDB
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Planets {

    @Id
    private String id;

    @NonNull
    private String name;

    private Double distanceLightYear;

    private Double radius;

    private Double mass;

    private Double orbitalPeriod;

    private Double temperature;

    private String starId;
}