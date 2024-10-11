package com.requestapi.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.requestapi.demo.dto.StarsDTO;
import com.requestapi.demo.exception.InvalidStarsException;
import com.requestapi.demo.model.Stars;
import com.requestapi.demo.repository.StarsRepository;

@Service
public class RequestStars {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StarsRepository starsRepository;

    public String fetchAndSaveStars(String starName) {
        String apiUrl = "https://api.api-ninjas.com/v1/stars?name=" + starName;
                                                                                                                                                                                                                                                                                                                                                            
        ResponseEntity<List<StarsDTO>> response = restTemplate.exchange(
            apiUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<StarsDTO>>() {}
        );
        //     ResponseEntity<MarvelApiResponseCharacters> responseEntity = restTemplate.getForEntity(apiUrl, MarvelApiResponseCharacters.class);

        List<StarsDTO> starsDTOList = response.getBody();

        if (starsDTOList != null) {
            for (StarsDTO starsDTO : starsDTOList) {
                if (starsDTO.getName() != null && !starsDTO.getName().isEmpty()) {
                    Optional<Stars> existingStarOptional = starsRepository.findByName(starsDTO.getName());

                    if (existingStarOptional.isPresent()) {
                        Stars existingStar = existingStarOptional.get();
                        // Atualiza os dados da estrela existente
                        existingStar.setConstellation(starsDTO.getConstellation());
                        existingStar.setRightAscension(starsDTO.getRightAscension());
                        existingStar.setDeclination(starsDTO.getDeclination());
                        existingStar.setApparentMagnitude(starsDTO.getApparentMagnitude());
                        existingStar.setAbsoluteMagnitude(starsDTO.getAbsoluteMagnitude());
                        existingStar.setDistanceLightYear(starsDTO.getDistanceLightYear());
                        existingStar.setSpectralClass(starsDTO.getSpectralClass());
                        starsRepository.save(existingStar); // Salva as atualizações no banco de dados
                    } else {
                        // Salva uma nova estrela
                        Stars newStar = new Stars();
                        newStar.setName(starsDTO.getName());
                        newStar.setConstellation(starsDTO.getConstellation());
                        newStar.setRightAscension(starsDTO.getRightAscension());
                        newStar.setDeclination(starsDTO.getDeclination());
                        newStar.setApparentMagnitude(starsDTO.getApparentMagnitude());
                        newStar.setAbsoluteMagnitude(starsDTO.getAbsoluteMagnitude());
                        newStar.setDistanceLightYear(starsDTO.getDistanceLightYear());
                        newStar.setSpectralClass(starsDTO.getSpectralClass());
                        starsRepository.save(newStar); // Salva o novo registro no banco de dados
                    }
                } else {
                    // Lança uma exceção em vez de apenas logar
                    throw new InvalidStarsException("Nome ausente ou vazio para estrela: " + starsDTO);
                }
            }
        } else {
            // Lança uma exceção se a lista de estrelas for nula
            throw new InvalidStarsException("Nenhum dado de estrela foi retornado da API para o nome da estrela: " + starName);
        }
        return "Dados salvos com sucesso";
    }
}
