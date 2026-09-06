package com.example.jdbc.model;

import com.example.jdbc.enums.AddressType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = 'addresses')
public class Address_U {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    private Long id;

    @Column(name = 'user_id')
    private Long user_id;

    @Column(name = 'address_line')
    private String addressLine;

    @Column(name = 'city')
    private String city;

    @Column(name = 'country')
    private String country;

    @Column(name = 'address_type')
    private AddressType addressType;

    @ManyToOne
    @JoinColumn('user_id')
    private User_U user;

    @Column(name = 'createdAt')
    private LocalDateTime createdAt;

    @Column(name = 'updatedAt')
    private LocalDateTime updatedAt;

    public Address_U() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
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

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public User_U getUser() {
        return user;
    }

    public void setUser(User_U user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}