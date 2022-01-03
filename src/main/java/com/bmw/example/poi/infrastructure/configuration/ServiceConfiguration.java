package com.bmw.example.poi.infrastructure.configuration;

import com.bmw.example.poi.PoiApplication;
import com.bmw.example.poi.domain.repository.PointOfInterestRepository;
import com.bmw.example.poi.domain.service.DomainPointOfInterestService;
import com.bmw.example.poi.domain.service.PointOfInterestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PoiApplication.class)
public class ServiceConfiguration {
    @Bean
    PointOfInterestService pointOfInterestService(final PointOfInterestRepository pointOfInterestRepository) {
        return new DomainPointOfInterestService(pointOfInterestRepository);
    }
}
