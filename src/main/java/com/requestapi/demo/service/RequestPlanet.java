package com.requestapi.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.requestapi.demo.dto.PlanetsDTO;
import com.requestapi.demo.exception.InvalidPlanetsException;
import com.requestapi.demo.model.Planets;
import com.requestapi.demo.repository.PlanetsRepository;

@Service
public class RequestPlanet {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PlanetsRepository planetRepository;

    public String fetchAndSavePlanets(String planetName) {
        String apiUrl = "https://api.api-ninjas.com/v1/planets?name=" + planetName;

        ResponseEntity<List<PlanetsDTO>> response = restTemplate.exchange(
            apiUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<PlanetsDTO>>() {}
        );

        List<PlanetsDTO> planetDTOList = response.getBody();

        if (planetDTOList != null) {
            for (PlanetsDTO planetDTO : planetDTOList) {
                if (planetDTO.getName() != null && !planetDTO.getName().isEmpty()) {
                    Optional<Planets> existingPlanetOptional = planetRepository.findByName(planetDTO.getName());

                    if (existingPlanetOptional.isPresent()) {
                        Planets existingPlanet = existingPlanetOptional.get();
                        // Atualiza os dados do planeta existente
                        existingPlanet.setMass(planetDTO.getMass());
                        existingPlanet.setRadius(planetDTO.getRadius());
                        existingPlanet.setOrbitalPeriod(planetDTO.getOrbitalPeriod());
                        existingPlanet.setTemperature(planetDTO.getTemperature());
                        existingPlanet.setDistanceLightYear(planetDTO.getDistanceLightYear());
                        planetRepository.save(existingPlanet); // Salva as atualizações no banco de dados
                    } else {
                        // Salva um novo planeta
                        Planets newPlanet = new Planets();
                        newPlanet.setName(planetDTO.getName());
                        newPlanet.setMass(planetDTO.getMass());
                        newPlanet.setRadius(planetDTO.getRadius());
                        newPlanet.setOrbitalPeriod(planetDTO.getOrbitalPeriod());
                        newPlanet.setTemperature(planetDTO.getTemperature());
                        newPlanet.setDistanceLightYear(planetDTO.getDistanceLightYear());
                        planetRepository.save(newPlanet); // Salva o novo registro no banco de dados
                    }
                } else {
                    // Lança uma exceção em vez de apenas logar
                    throw new InvalidPlanetsException("Nome ausente ou vazio para o planeta: " + planetDTO);
                }
            }
        } else {
            // Lança uma exceção se a lista de planetas for nula
            throw new InvalidPlanetsException("Nenhum dado de planeta foi retornado da API para o nome do planeta: " + planetName);
        }
        return "Dados dos planetas salvos com sucesso";
    }
}