package com.bmw.example.poi.infrastructure.repository.postgres;

import com.bmw.example.poi.domain.PointOfInterest;
import com.bmw.example.poi.domain.repository.PointOfInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PostgresDbPointOfInterestRepository implements PointOfInterestRepository {
    private final SpringDataPostgresPointOfInterestRepository pointOfInterestRepository;

    @Autowired
    public PostgresDbPointOfInterestRepository(SpringDataPostgresPointOfInterestRepository pointOfInterestRepository) {
        this.pointOfInterestRepository = pointOfInterestRepository;
    }

    @Override
    public Mono<PointOfInterest> findById(Long id) {
        return pointOfInterestRepository
                .findById(id)
                .map(PointOfInterestEntity::toPointOfInterest);
    }

    @Override
    public Mono<PointOfInterest> save(PointOfInterest poi) {
        return pointOfInterestRepository
                .save(new PointOfInterestEntity(poi))
                .map(PointOfInterestEntity::toPointOfInterest);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return pointOfInterestRepository.deleteById(id);
    }

    @Override
    public Flux<PointOfInterest> findByType(String type) {
        return pointOfInterestRepository
                .findByPoiType(PoiTypeEntity.valueOf(type))
                .map(PointOfInterestEntity::toPointOfInterest);
    }
}
