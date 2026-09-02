package com.example.jdbc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class Profiles {

    private int id;

    private int studentId;

    private LocalDateTime dateOfBirth;

    private String gender;

    private String bio;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Profiles() {}

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

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

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {

    }
}