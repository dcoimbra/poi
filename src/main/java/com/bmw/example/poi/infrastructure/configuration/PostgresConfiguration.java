package com.bmw.example.poi.infrastructure.configuration;

import com.bmw.example.poi.infrastructure.repository.postgres.SpringDataPostgresPointOfInterestRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackageClasses = SpringDataPostgresPointOfInterestRepository.class)
public class PostgresConfiguration {
}
