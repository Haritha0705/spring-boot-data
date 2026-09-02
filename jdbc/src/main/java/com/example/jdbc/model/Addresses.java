package com.example.jdbc.model;

import com.example.jdbc.enums.AddressType;
import java.time.LocalDateTime;

public class Addresses {

    private int id;

    private int studentId;

    private String addressLine;

    private String city;

    private String country;

    private AddressType addressType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Addresses(int id, int studentId, String addressLine, String city, String country, AddressType addressType) {
        this.id = id;
        this.studentId = studentId;
        this.addressLine = addressLine;
        this.city = city;
        this.country = country;
        this.addressType = addressType;
    }

    public Addresses() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return null;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
    }
}