package com.bmw.example.poi.domain;

import java.util.Objects;

class Address {
    private final String addressName;
    private final String city;
    private final String zipCode;

    Address(String addressName, String city, String zipCode) {
        this.addressName = addressName;
        this.city = city;
        this.zipCode = zipCode;
    }

    String getAddressName() {
        return addressName;
    }

    String getCity() {
        return city;
    }

    String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressName, address.addressName) && Objects.equals(city, address.city) && Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressName, city, zipCode);
    }

}
