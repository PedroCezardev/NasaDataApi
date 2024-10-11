package com.requestapi.demo.model;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Planets")
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

    @ManyToOne
    private String starId;
}