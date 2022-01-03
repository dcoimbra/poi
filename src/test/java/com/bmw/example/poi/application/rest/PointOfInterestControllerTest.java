package com.bmw.example.poi.application.rest;

import com.bmw.example.poi.application.dto.PointOfInterestDto;
import com.bmw.example.poi.infrastructure.repository.postgres.SpringDataPostgresPointOfInterestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@SpringBootTest
class PointOfInterestControllerTest {

    private final WebTestClient webTestClient = WebTestClient
            .bindToServer()
            .baseUrl("http://localhost:9090/poi")
            .build();
    @Autowired
    SpringDataPostgresPointOfInterestRepository pointOfInterestRepository;
    @Autowired
    PointOfInterestController controller;
    PointOfInterestDto dto = new PointOfInterestDto(
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
    PointOfInterestDto expectedDto;

    @BeforeEach
    void setUp() {
        expectedDto = controller.createPointOfInterest(Mono.just(dto)).block();
    }

    @AfterEach
    void tearDown() {
        pointOfInterestRepository.deleteAll().block();
    }

    @Test
    void getPointOfInterest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("id", expectedDto.getId()).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PointOfInterestDto.class)
                .isEqualTo(expectedDto);
    }

    @Test
    void listPointsOfInterestByType() {
        webTestClient.get()
                .uri("/DEALER")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PointOfInterestDto.class)
                .consumeWith(listEntityExchangeResult ->
                        Objects.requireNonNull(listEntityExchangeResult.getResponseBody())
                                .forEach(dto -> Assertions.assertEquals("DEALER", dto.getPoiType())));
    }

    @Test
    void createPointOfInterest() {
        webTestClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(dto), PointOfInterestDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(PointOfInterestDto.class)
                .isEqualTo(dto);
    }

    @Test
    void updatePointOfInterest() {
        expectedDto.setCity("lisbon");

        webTestClient.put()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(expectedDto), PointOfInterestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(PointOfInterestDto.class)
                .isEqualTo(expectedDto);
    }

    @Test
    void deletePointOfInterest() {
        webTestClient.delete()
                .uri(uriBuilder -> uriBuilder.queryParam("id", expectedDto.getId()).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .isEmpty();
    }
}