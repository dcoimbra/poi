package com.bmw.example.poi.domain.service;

import com.bmw.example.poi.domain.PointOfInterest;
import com.bmw.example.poi.domain.repository.PointOfInterestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DomainPointOfInterestServiceTest {

    @Mock
    private PointOfInterestRepository pointOfInterestRepository;

    @InjectMocks
    private DomainPointOfInterestService pointOfInterestService;

    private Mono<PointOfInterest> poiCreateMono;
    private PointOfInterest poiCreate;

    private Mono<PointOfInterest> poiGetMono;
    private PointOfInterest poiGet;


    @BeforeEach
    void setUp() {

        poiCreate = new PointOfInterest(null,
                "poi1",
                "desc",
                "DEALER",
                "address",
                "porto",
                "1234-567",
                12.45,
                56.78);
        poiCreateMono = Mono.just(poiCreate);

        poiGet = new PointOfInterest(1L,
                "poi1",
                "desc",
                "DEALER",
                "address",
                "porto",
                "1234-567",
                12.45,
                56.78);
        poiGetMono = Mono.just(poiGet);
    }

    @Test
    void getPointOfInterest() {
        Mockito.when(pointOfInterestRepository.findById(any())).thenReturn(poiGetMono);
        Mono<PointOfInterest> resultMono = poiGetMono.flatMap(poi ->
                pointOfInterestService.getPointOfInterest(poi.getId()));

        StepVerifier
                .create(resultMono)
                .consumeNextWith(resultPoi -> {
                    assertEquals(resultPoi, poiGet);
                    Mockito.verify(pointOfInterestRepository).findById(resultPoi.getId());
                })
                .verifyComplete();
    }

    @Test
    void createPointOfInterest() {
        Mockito.when(pointOfInterestRepository.save(any())).thenReturn(poiCreateMono);
        Mono<PointOfInterest> resultMono = poiCreateMono.flatMap(pointOfInterestService::createPointOfInterest);

        StepVerifier
                .create(resultMono)
                .consumeNextWith(resultPoi -> {
                    assertEquals(resultPoi, poiCreate);
                    Mockito.verify(pointOfInterestRepository).save(poiCreate);
                })
                .verifyComplete();
    }

    @Test
    void updatePointOfInterest() {
        Mockito.when(pointOfInterestRepository.findById(any())).thenReturn(poiGetMono);
        Mockito.when(pointOfInterestRepository.save(any())).thenReturn(poiGetMono);
        Mono<PointOfInterest> resultMono = poiGetMono.flatMap(pointOfInterestService::updatePointOfInterest);

        StepVerifier
                .create(resultMono)
                .consumeNextWith(resultPoi -> {
                    assertEquals(resultPoi, poiGet);
                    Mockito.verify(pointOfInterestRepository).save(poiGet);
                    Mockito.verify(pointOfInterestRepository).save(poiGet);
                })
                .verifyComplete();
    }

    @Test
    void deletePointOfInterest() {
        Mockito.when(pointOfInterestRepository.findById(any())).thenReturn(poiGetMono);
        Mockito.when(pointOfInterestRepository.deleteById(any())).thenReturn(Mono.empty());
        Mono<Void> resultMono = poiGetMono.flatMap(poi -> pointOfInterestService.deletePointOfInterest(poi.getId()));

        StepVerifier
                .create(resultMono)
                .verifyComplete();

        Mockito.verify(pointOfInterestRepository).deleteById(poiGet.getId());
    }

    @Test
    void listPointsOfInterestByType() {

        PointOfInterest poi1 = new PointOfInterest(1L,
                "poi2",
                "desc",
                "DEALER",
                "add1",
                "porto",
                "1234-567",
                12.456,
                56.789);

        PointOfInterest poi2 = new PointOfInterest(2L,
                "poi3",
                "desc",
                "DEALER",
                "add2",
                "porto",
                "1234-567",
                12.456,
                56.789);

        Iterable<PointOfInterest> poiTypeIterable = Arrays.asList(poi1, poi2);
        Flux<PointOfInterest> poiTypeFlux = Flux.fromIterable(poiTypeIterable);

        Mockito.when(pointOfInterestRepository.findByType("DEALER")).thenReturn(poiTypeFlux);
        Flux<PointOfInterest> resultFlux = pointOfInterestService.listPointsOfInterestByType("DEALER");

        StepVerifier
                .create(resultFlux)
                .expectNext(poi1)
                .expectNext(poi2)
                .verifyComplete();

        Mockito.verify(pointOfInterestRepository).findByType("DEALER");
    }
}