package com.requestapi.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.requestapi.demo.exception.InvalidStarsException;
import com.requestapi.demo.model.Stars;
import com.requestapi.demo.repository.StarsRepository;

@Service
public class StarsService {
    
    @Autowired
    private StarsRepository starsRepository;

    public List<Stars> getAllStars() {
        List<Stars> stars = starsRepository.findAll();
        if (stars.isEmpty()) {
            throw new InvalidStarsException("Nenhuma estrela encontrada.");
        }
        return stars;
    }

    public Stars insertByStars(Stars stars) {
        validateStars(stars);
        return starsRepository.save(stars);
    }

    public Optional<Stars> getByName(String name){
        Optional<Stars> stars = starsRepository.findByName(name);
        if(stars.isEmpty()) {
            throw new InvalidStarsException("A estrela não foi encontrada com o Nome: " + name);
        }
        return stars;
    }

    public List<Stars> getByContellation(String constellation) {
        List<Stars> stars = starsRepository.findByConstellation(constellation);
        if(stars.isEmpty()) {
            throw new InvalidStarsException("A estrela não foi encontrada com a Constelacao: " + constellation);
        }
        return stars;
    }

    public List<Stars> getByDistanceLightYear(Double distanceLightYear) {
        List<Stars> stars = starsRepository.findByDistanceLightYear(distanceLightYear);
        if(stars.isEmpty()) {
            throw new InvalidStarsException("A estrela não foi encontrada com a Distancia de Anos Luz: " + distanceLightYear);
        }
        return stars;
    }

    public List<Stars> getBySpectralClass(String spectralClass) {
        List<Stars> stars = starsRepository.findBySpectralClass(spectralClass);
        if(stars.isEmpty()) {
            throw new InvalidStarsException("A estrela não foi encontrada com a Classe Spectral: " + spectralClass);
        }
        return stars;
    }

    public void deleteById(String Id) {
        if (!starsRepository.existsById(Id)) {
            throw new InvalidStarsException("A estrela com este Id não foi encontrado no sistema.");
        }
        starsRepository.deleteById(Id);
        System.out.println("A estrela foi deletada com sucesso!");
    }

    public void deleteByName(String name) {
        if (!starsRepository.existsByName(name)) {
            throw new InvalidStarsException("A estrela com este Nome não foi encontrado no sistema.");
        }
        starsRepository.deleteByName(name);
        System.out.println("A estrela foi deletada com sucesso!");
    }

    public Stars updateStarsById(String id, Stars starsDetails) {

        if(starsDetails == null) {
            throw new InvalidStarsException("Os detalhes da Estrelas não podem ser nulos.");
        }

        Optional<Stars> optionalStars = starsRepository.findById(id);
        if (optionalStars.isEmpty()) {
            throw new InvalidStarsException("Estrela não encontrada com id: " + id);
        }

        validateStars(starsDetails);
        Stars stars = optionalStars.get();

        stars.setName(starsDetails.getName());
        stars.setConstellation(starsDetails.getConstellation());
        stars.setRightAscension(starsDetails.getRightAscension());
        stars.setDeclination(starsDetails.getDeclination());
        stars.setApparentMagnitude(starsDetails.getApparentMagnitude());
        stars.setAbsoluteMagnitude(starsDetails.getAbsoluteMagnitude());
        stars.setDistanceLightYear(starsDetails.getDistanceLightYear());
        stars.setSpectralClass(starsDetails.getSpectralClass());

        Stars updatedStars = starsRepository.save(stars);
        return updatedStars;
    }
    
    public void validateStars(Stars stars) {
        if ( stars.getName().isEmpty() || stars.getName().length() > 50) {
            throw new InvalidStarsException("O Nome da Estrela não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if ( stars.getConstellation().isEmpty() || stars.getConstellation().length() > 50) {
            throw new InvalidStarsException("A constelação da Estrela não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if ( stars.getRightAscension().isEmpty() || stars.getRightAscension().length() > 50) {
            throw new InvalidStarsException("A Ascensão Direita da Estrela não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if ( stars.getDeclination().isEmpty() || stars.getDeclination().length() > 50) {
            throw new InvalidStarsException("A Declinação da Estrela não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
        if ( stars.getApparentMagnitude() == null || stars.getApparentMagnitude() <= 0) {
            throw new InvalidStarsException("A Magnitude Aparente da Estrela não pode ser nula, menor ou igual a 0.");
        }
        if ( stars.getAbsoluteMagnitude() == null || stars.getAbsoluteMagnitude() <= 0) {
            throw new InvalidStarsException("A Magnitude Absoluta da Estrela não pode ser nula, menor ou igual a 0.");
        }
        if ( stars.getDistanceLightYear() == null || stars.getDistanceLightYear() <= 0) {
            throw new InvalidStarsException("Os Anos Luz de Distancia da Estrela não pode ser nula, menor ou igual a 0.");
        }
        if ( stars.getSpectralClass().isEmpty() || stars.getSpectralClass().length() > 50) {
            throw new InvalidStarsException("A Classe Spectral da Estrela não pode ser nulo, vazio ou ter mais de 50 caracteres");
        }
    }

}

