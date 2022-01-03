package com.bmw.example.poi.application.dto;

import com.bmw.example.poi.domain.PointOfInterest;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class PointOfInterestDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String poiType;
    @NotBlank
    private String addressName;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    private double latitude;
    private double longitude;

    public PointOfInterestDto() {}

    public PointOfInterestDto(PointOfInterest poi) {
        this(poi.getId(),
                poi.getName(),
                poi.getDescription(),
                poi.getPoiType(),
                poi.getAddressName(),
                poi.getCity(),
                poi.getZipCode(),
                poi.getLatitude(),
                poi.getLongitude());
    }

    public PointOfInterestDto(Long id,
                              String name,
                              String description,
                              String poiType,
                              String addressName,
                              String city,
                              String zipCode,
                              double latitude,
                              double longitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.poiType = poiType;
        this.addressName = addressName;
        this.city = city;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public PointOfInterest toPoi() {
        return new PointOfInterest(this.id,
                this.name,
                this.description,
                this.poiType,
                this.addressName,
                this.city,
                this.zipCode,
                this.latitude,
                this.longitude);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointOfInterestDto that = (PointOfInterestDto) o;
        return Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0 &&
                name.equals(that.name) &&
                description.equals(that.description) &&
                poiType.equals(that.poiType) &&
                addressName.equals(that.addressName) &&
                city.equals(that.city) &&
                zipCode.equals(that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, poiType, addressName, city, zipCode, latitude, longitude);
    }

    @Override
    public String toString() {
        return "PointOfInterestDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", poiType='" + poiType + '\'' +
                ", addressName='" + addressName + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
