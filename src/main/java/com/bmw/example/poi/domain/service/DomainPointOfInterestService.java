package com.bmw.example.poi.domain.service;

import com.bmw.example.poi.domain.PointOfInterest;
import com.bmw.example.poi.domain.repository.PointOfInterestRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Domain service for exposing CRUD operations
 */
public class DomainPointOfInterestService implements PointOfInterestService {

    private final PointOfInterestRepository pointOfInterestRepository;

    public DomainPointOfInterestService(PointOfInterestRepository pointOfInterestRepository) {
        this.pointOfInterestRepository = pointOfInterestRepository;
    }

    @Override
    public Mono<PointOfInterest> getPointOfInterest(Long id) {
        return pointOfInterestRepository.findById(id);
    }

    @Override
    public Mono<PointOfInterest> createPointOfInterest(PointOfInterest poi) {
        return pointOfInterestRepository.save(poi);
    }

    @Override
    public Mono<PointOfInterest> updatePointOfInterest(PointOfInterest poi) {
        return pointOfInterestRepository.findById(poi.getId())
                .flatMap(existingPoi -> pointOfInterestRepository.save(poi));
    }

    @Override
    public Mono<Void> deletePointOfInterest(Long id) {
        return pointOfInterestRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException()))
                .flatMap(poi -> pointOfInterestRepository.deleteById(poi.getId()));
    }

    @Override
    public Flux<PointOfInterest> listPointsOfInterestByType(String type) {
        return pointOfInterestRepository.findByType(type);
    }
}
