package com.example.jdbc.model;

import com.example.jdbc.enums.ContactType;
import java.time.LocalDateTime;

public class Contacts {

    private int id;

    private int studentId;

    private ContactType contactType;

    private String contactValue;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Contacts() {}

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

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {

    }
}