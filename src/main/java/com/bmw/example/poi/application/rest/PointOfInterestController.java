package com.bmw.example.poi.application.rest;

import com.bmw.example.poi.application.dto.PointOfInterestDto;
import com.bmw.example.poi.domain.service.PointOfInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for CRUD operations on the POI database. Uses Spring WebFlux.
 */
@RestController
@RequestMapping("/poi")
class PointOfInterestController {
    private final PointOfInterestService pointOfInterestService;

    @Autowired
    PointOfInterestController(PointOfInterestService pointOfInterestService) {
        this.pointOfInterestService = pointOfInterestService;
    }

    @GetMapping(name = "id", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity<PointOfInterestDto>> getPointOfInterest(@RequestParam final Long id) {
        return pointOfInterestService.getPointOfInterest(id)
                .map(PointOfInterestDto::new)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    Flux<PointOfInterestDto> listPointsOfInterestByType(@PathVariable final String type) {
        return pointOfInterestService.listPointsOfInterestByType(type)
                .map(PointOfInterestDto::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    Mono<PointOfInterestDto> createPointOfInterest(@RequestBody final Mono<PointOfInterestDto> dtoMono) {
        return dtoMono.flatMap(dto -> pointOfInterestService.createPointOfInterest(dto.toPoi()))
                .map(PointOfInterestDto::new);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity<PointOfInterestDto>> updatePointOfInterest(@RequestBody final Mono<PointOfInterestDto> dtoMono) {
        return dtoMono.flatMap(dto -> pointOfInterestService.updatePointOfInterest(dto.toPoi())
                .map(PointOfInterestDto::new)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
        );
    }

    @DeleteMapping(name = "{id}")
    Mono<ResponseEntity<Void>> deletePointOfInterest(@RequestParam("id") final Long id) {
        return pointOfInterestService.deletePointOfInterest(id)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.notFound().build());
    }
}
