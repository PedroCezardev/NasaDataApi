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

import com.requestapi.demo.exception.InvalidStarsException;
import com.requestapi.demo.model.Stars;
import com.requestapi.demo.service.RequestStars;
import com.requestapi.demo.service.StarsService;


@RestController
@RequestMapping("api/stars")
public class StarsController {

    @Autowired
    private StarsService starsService;

    @Autowired
    private RequestStars requestStars;

    @GetMapping("/savestars/{starName}")
    public String fetchSaveData(@PathVariable String starName) {
        try{
            return requestStars.fetchAndSaveStars(starName);
        } catch (InvalidStarsException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/add")
    public Stars insertStars(@RequestBody Stars stars) {
        try {
            return starsService.insertByStars(stars);
        } catch (InvalidStarsException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/allstars")
    public List<Stars> getAllData() {
        try{
            return starsService.getAllStars();
        } catch (InvalidStarsException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/nome/{name}")
    public Optional<Stars> getName(@PathVariable String name) {
        try{
            return starsService.getByName(name);
        } catch (InvalidStarsException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/constelacao/{constellation}")
    public List<Stars> getConstellation(@PathVariable String constellation) {
        try{
            return starsService.getByContellation(constellation);
        } catch (InvalidStarsException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @GetMapping("/distancia/{distanceLightYear}")
    public List<Stars> getDistanceLightYear(@PathVariable Double distanceLightYear) {
        try{
            return starsService.getByDistanceLightYear(distanceLightYear);
        } catch (InvalidStarsException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/classe/{spectralClass}")
    public List<Stars> getSpectralClass(@PathVariable String spectralClass) {
        try{
            return starsService.getBySpectralClass(spectralClass);
        } catch (InvalidStarsException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @DeleteMapping("/delete/{Id}")
    public void deleteStarsById(@PathVariable Long Id) {
        try{
            starsService.deleteById(Id);
        } catch (InvalidStarsException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/name/{name}")
    public void deleteStarsByName(@PathVariable String name) {
        try{
            starsService.deleteByName(name);
        } catch (InvalidStarsException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStars(@PathVariable Long id, @RequestBody Stars starsDetails) {
        try {
            Stars updatedStars = starsService.updateStarsById(id, starsDetails);
            return ResponseEntity.ok(updatedStars);
        } catch (InvalidStarsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado.");
        }
    }

}
