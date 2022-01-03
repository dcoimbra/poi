package com.bmw.example.poi.infrastructure.repository.postgres;

import com.bmw.example.poi.domain.PointOfInterest;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PointOfInterestEntity {
    @Id
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private PoiTypeEntity poiType;
    @NotBlank
    private String addressName;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    private double latitude;
    private double longitude;

    public PointOfInterestEntity(PointOfInterest poi) {
        this.id = poi.getId();
        this.name = poi.getName();
        this.description = poi.getDescription();
        this.poiType = PoiTypeEntity.valueOf(poi.getPoiType());
        this.addressName = poi.getAddressName();
        this.city = poi.getCity();
        this.zipCode = poi.getZipCode();
        this.latitude = poi.getLatitude();
        this.longitude = poi.getLongitude();
    }

    public PointOfInterestEntity() {}

    public PointOfInterest toPointOfInterest() {
        return new PointOfInterest(
                id,
                name,
                description,
                poiType.name(),
                addressName,
                city,
                zipCode,
                latitude,
                longitude
        );
    }
}
