package com.bmw.example.poi.domain.repository;

import com.bmw.example.poi.domain.PointOfInterest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Domain interface for operations on the database. Implemented in the infrastructure layer.
 */
public interface PointOfInterestRepository {
    Mono<PointOfInterest> findById(Long id);

    Mono<PointOfInterest> save(PointOfInterest poi);

    Mono<Void> deleteById(Long id);

    Flux<PointOfInterest> findByType(String type);
}
