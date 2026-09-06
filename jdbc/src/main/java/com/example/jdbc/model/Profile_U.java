package com.example.jdbc.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = 'profiles')
public class Profile_U {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    private Long id;

    private int studentId;

    private LocalDateTime dateOfBirth;

    private String gender;

    private String bio;

    @OneToOne
    @JoinColumn(name = 'id')
    @MapsId
    private User_U user;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Profile_U() {}

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        this.createdAt = createdAt;
    }
}