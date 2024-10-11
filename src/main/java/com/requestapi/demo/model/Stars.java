package com.requestapi.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Document(collection = "Stars") // Definindo como um documento MongoDB
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stars {
    
    @Id
    private String id;

    @NonNull
    private String name;

    private String constellation;

    private String rightAscension;

    private String declination;

    private Double apparentMagnitude;

    private Double absoluteMagnitude;

    private Double distanceLightYear;

    private String spectralClass;

    @DBRef
    private List<Planets> planets; // Lista de planetas associados a esta estrela

}
