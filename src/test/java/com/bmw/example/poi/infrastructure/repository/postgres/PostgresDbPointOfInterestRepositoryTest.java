package com.bmw.example.poi.infrastructure.repository.postgres;

import com.bmw.example.poi.domain.PointOfInterest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PostgresDbPointOfInterestRepositoryTest {

    @Autowired
    SpringDataPostgresPointOfInterestRepository springDataPostgresPointOfInterestRepository;

    @Autowired
    PostgresDbPointOfInterestRepository pointOfInterestRepository;

    PointOfInterest poi = new PointOfInterest(
            null,
            "poi1",
            "desc",
            "DEALER",
            "add1",
            "porto",
            "1234-567",
            123.45,
            67.67
    );

    Mono<PointOfInterest> expectedPoiMono;

    @BeforeEach
    void setUp() {
        expectedPoiMono = pointOfInterestRepository.save(poi);
    }

    @AfterEach
    void tearDown() {
        springDataPostgresPointOfInterestRepository.deleteAll().block();
    }

    @Test
    void findById() {
        Mono<PointOfInterest> resultMono = expectedPoiMono.flatMap(poi -> pointOfInterestRepository.findById(poi.getId()));

        StepVerifier
                .create(resultMono)
                .consumeNextWith(resultPoi -> assertEquals(resultPoi, poi))
                .verifyComplete();
    }

    @Test
    void save() {
        Mono<PointOfInterest> resultMono = expectedPoiMono.flatMap(pointOfInterestRepository::save);

        StepVerifier
                .create(resultMono)
                .consumeNextWith(resultPoi -> assertEquals(resultPoi, poi))
                .verifyComplete();
    }

    @Test
    void deleteById() {
        Mono<PointOfInterest> resultMono = expectedPoiMono.flatMap(poi -> pointOfInterestRepository.deleteById(poi.getId())
                .then(pointOfInterestRepository.findById(poi.getId())));

        StepVerifier
                .create(resultMono)
                .verifyComplete();
    }

    @Test
    void findByType() {
        Flux<PointOfInterest> resultFlux = expectedPoiMono.flatMapMany(poi -> pointOfInterestRepository.findByType(poi.getPoiType()));

        StepVerifier.create(resultFlux)
                .consumeNextWith(poi -> Assertions.assertEquals("DEALER", poi.getPoiType()))
                .verifyComplete();
    }
}
