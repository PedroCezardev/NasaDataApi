package com.requestapi.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.requestapi.demo.exception.InvalidPlanetsException;
import com.requestapi.demo.model.Planets;
import com.requestapi.demo.repository.PlanetsRepository;

@Service
public class PlanetsService {

    @Autowired
    private PlanetsRepository planetsRepository;

    public List<Planets> getAllPlanets() {
        List<Planets> planets = planetsRepository.findAll();
        if (planets.isEmpty()) {
            throw new InvalidPlanetsException("Nenhum planeta encontrado.");
        }
        return planets;
    }

    public Planets insertByPlanets(Planets planets) {
        validatePlanets(planets);
        return planetsRepository.save(planets);
    }

    public Optional<Planets> getByName(String name) {
        Optional<Planets> planets = planetsRepository.findByName(name);
        if (planets.isEmpty()) {
            throw new InvalidPlanetsException("O planeta não foi encontrado com o Nome: " + name);
        }
        return planets;
    }

    public void deleteById(String id) {
        if (!planetsRepository.existsById(id)) {
            throw new InvalidPlanetsException("O planeta com este Id não foi encontrado no sistema.");
        }
        planetsRepository.deleteById(id);
        System.out.println("O planeta foi deletado com sucesso!");
    }

    public Planets updatePlanetsById(String id, Planets planetsDetails) {
        if (planetsDetails == null) {
            throw new InvalidPlanetsException("Os detalhes do planeta não podem ser nulos.");
        }

        Optional<Planets> optionalPlanets = planetsRepository.findById(id);
        if (optionalPlanets.isEmpty()) {
            throw new InvalidPlanetsException("Planeta não encontrado com id: " + id);
        }

        validatePlanets(planetsDetails);
        Planets planets = optionalPlanets.get();

        planets.setName(planetsDetails.getName());
        planets.setMass(planetsDetails.getMass());
        planets.setRadius(planetsDetails.getRadius());
        planets.setOrbitalPeriod(planetsDetails.getOrbitalPeriod());
        planets.setTemperature(planetsDetails.getTemperature());
        planets.setDistanceLightYear(planetsDetails.getDistanceLightYear()); // Adicionando distanceLightYear

        return planetsRepository.save(planets);
    }

    private void validatePlanets(Planets planets) {
        if (planets.getName().isEmpty() || planets.getName().length() > 50) {
            throw new InvalidPlanetsException("O Nome do Planeta não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if (planets.getMass() == null || planets.getMass() <= 0) {
            throw new InvalidPlanetsException("A Massa do Planeta não pode ser nula ou menor ou igual a 0.");
        }
        if (planets.getRadius() == null || planets.getRadius() <= 0) {
            throw new InvalidPlanetsException("O Raio do Planeta não pode ser nulo ou menor ou igual a 0.");
        }
        if (planets.getOrbitalPeriod() == null || planets.getOrbitalPeriod() <= 0) {
            throw new InvalidPlanetsException("O Período Orbital não pode ser nulo ou menor ou igual a 0.");
        }
        if (planets.getTemperature() == null || planets.getTemperature() <= 0) {
            throw new InvalidPlanetsException("A Temperatura do Planeta não pode ser nula ou menor ou igual a 0.");
        }
        if (planets.getDistanceLightYear() == null || planets.getDistanceLightYear() <= 0) { // Validação de distanceLightYear
            throw new InvalidPlanetsException("A Distância em Anos Luz do Planeta não pode ser nula ou menor ou igual a 0.");
        }
    }
}