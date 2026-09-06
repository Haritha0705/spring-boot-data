package com.example.jdbc.model;

import com.example.jdbc.enums.ContactType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = 'contacts')
public class Contacts_U {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    private Long id;

    @Column(name = 'user_id')
    private Long user_id;

    private ContactType contactType;

    private String contactValue;

    @ManyToOne
    @JoinColumn('user_id')
    private User_U user;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Contacts_U() {}

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