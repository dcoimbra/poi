package com.bmw.example.poi.domain.service;

import com.bmw.example.poi.domain.PointOfInterest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PointOfInterestService {
    Mono<PointOfInterest> getPointOfInterest(Long id);

    Mono<PointOfInterest> createPointOfInterest(PointOfInterest poi);

    Mono<PointOfInterest> updatePointOfInterest(PointOfInterest poi);

    Mono<Void> deletePointOfInterest(Long id);

    Flux<PointOfInterest> listPointsOfInterestByType(String type);
}
