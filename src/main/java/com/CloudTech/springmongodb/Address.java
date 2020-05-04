package com.CloudTech.springmongodb;

import org.springframework.data.annotation.Id;

public class Address {
    @Id
    String id;
    String city;
    String country;

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
