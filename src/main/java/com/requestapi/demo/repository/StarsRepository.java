    package com.requestapi.demo.repository;

    import java.util.List;
    import java.util.Optional;

    import org.springframework.data.mongodb.repository.MongoRepository;
    import org.springframework.stereotype.Repository;

    import com.requestapi.demo.model.Stars;

    @Repository
    public interface StarsRepository extends MongoRepository<Stars, String> {
        
        Optional<Stars> findByName(String name);

        public List<Stars> findByConstellation(String constellation);

        public Stars findByRightAscension(String rightAscension);

        public Stars findByDeclination(String declination);

        public Stars findByApparentMagnitude(Double apparentMagnitude);

        public Stars findByAbsoluteMagnitude(Double absoluteMagnitude);

        public List<Stars> findByDistanceLightYear(Double distanceLightYear);

        public List<Stars> findBySpectralClass(String spectralClass);

        public boolean existsByName(String name);   

        public void deleteByName(String name);

    }
