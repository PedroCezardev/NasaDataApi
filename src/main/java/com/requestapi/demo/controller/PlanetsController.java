package com.requestapi.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.requestapi.demo.exception.InvalidPlanetsException;
import com.requestapi.demo.exception.InvalidStarsException;
import com.requestapi.demo.model.Planets;
import com.requestapi.demo.service.PlanetsService;
import com.requestapi.demo.service.RequestPlanet;

@RestController
@RequestMapping("api/planets")
public class PlanetsController {

    @Autowired
    private PlanetsService planetsService;

    @Autowired
    private RequestPlanet requestPlanet;

    @GetMapping("/saveplanets/{planetName}")
    public String fetchSaveData(@PathVariable String planetName) {
        try{
            return requestPlanet.fetchAndSavePlanets(planetName);
        } catch (InvalidStarsException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/add")
    public Planets insertPlanets(@RequestBody Planets planets) {
        try {
            return planetsService.insertByPlanets(planets);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/allplanets")
    public List<Planets> getAllPlanets() {
        try {
            return planetsService.getAllPlanets();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/nome/{name}")
    public ResponseEntity<Optional<Planets>> getName(@PathVariable String name) {
        try {
            Optional<Planets> planet = planetsService.getByName(name);
            if (planet.isPresent()) {
                return new ResponseEntity<>(planet, HttpStatus.OK);
            } else {
                throw new InvalidPlanetsException("Planeta com o nome " + name + " não encontrado.");
            }
        } catch (InvalidPlanetsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<HttpStatus> deletePlanetsById(@PathVariable String Id) {
        try {
            planetsService.deleteById(Id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePlanets(@PathVariable String id, @RequestBody Planets planetsDetails) {
        try {
            Planets updatedPlanets = planetsService.updatePlanetsById(id, planetsDetails);
            return new ResponseEntity<>(updatedPlanets, HttpStatus.OK);
        } catch (InvalidPlanetsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}