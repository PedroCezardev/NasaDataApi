package com.requestapi.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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

    @OneToMany
    private List<Planets> planets;

}
