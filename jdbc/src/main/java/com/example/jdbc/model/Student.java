package com.example.jdbc.model;

import java.time.LocalDateTime;

public class Student {

    private Long id;

    private String name;

    private String email;

    private Integer age;

    private Addresses addresses;

    private Contacts contacts;

    private Profiles profiles;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Student () {};

    public Student(String name, String email, Integer age, Addresses addresses, Contacts contacts, Profiles profiles) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.addresses = addresses;
        this.contacts = contacts;
        this.profiles = profiles;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Addresses getAddresses() {
        return addresses;
    }

    public void setAddresses(Addresses addresses) {
        this.addresses = addresses;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public Profiles getProfiles() {
        return profiles;
    }

    public void setProfiles(Profiles profiles) {
        this.profiles = profiles;
    }

}