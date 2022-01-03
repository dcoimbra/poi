package com.bmw.example.poi.domain;

import java.util.Objects;

public class PointOfInterest {
    private final Long id;
    private final String name;
    private final String description;
    private final PoiType poiType;
    private final Address address;
    private final Coordinate coordinate;

    public PointOfInterest(Long id,
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
        this.poiType = PoiType.valueOf(poiType);
        this.address = new Address(addressName, city, zipCode);
        this.coordinate = new Coordinate(latitude, longitude);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPoiType() {
        return poiType.name();
    }

    public String getAddressName() {
        return this.address.getAddressName();
    }

    public String getCity() {
        return this.address.getCity();
    }

    public String getZipCode() {
        return this.address.getZipCode();
    }

    public double getLatitude() {
        return this.coordinate.getLatitude();
    }

    public double getLongitude() {
        return this.coordinate.getLongitude();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointOfInterest that = (PointOfInterest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                poiType == that.poiType &&
                Objects.equals(address, that.address) &&
                Objects.equals(coordinate, that.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, poiType, address, coordinate);
    }
}
