package com.bmw.example.poi.infrastructure.repository.postgres;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SpringDataPostgresPointOfInterestRepository extends ReactiveCrudRepository<PointOfInterestEntity, Long> {
    Flux<PointOfInterestEntity> findByPoiType(PoiTypeEntity poiType);
}
