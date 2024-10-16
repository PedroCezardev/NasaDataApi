package com.requestapi.demo.model;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Stars")
public class Stars {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    private String constellation;

    private String rightAscension;

    private String declination;

    private Double apparentMagnitude;

    private Double absoluteMagnitude;

    private Double distanceLightYear;

    private String spectralClass;

    @OneToMany(mappedBy = "star")
    private List<Planets> planets;
}
