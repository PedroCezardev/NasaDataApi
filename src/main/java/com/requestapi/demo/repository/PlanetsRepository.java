package com.requestapi.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.requestapi.demo.model.Planets;

@Repository
public interface PlanetsRepository extends MongoRepository<Planets, String> {

    Optional<Planets> findByName(String name);

    public List<Planets> findByDistanceLightYear(Double distanceLighYear);

    public Planets findByRadius(Double radius);

    public Planets findByMass(Double mass);

    public Planets findByOrbitalPeriod(Double orbitalPeriod);

    public List<Planets> findByTemperature(Double temperature);

}
