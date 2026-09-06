package com.example.jdbc.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = 'notifications')
public class Notification_U {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    private int id;

    @Column(name = 'user_id')
    private Long user_id;

    private String title;

    private String message;

    private Boolean isRead;

    @ManyToOne
    @JoinColumn('user_id')
    private User_U user;

    private LocalDateTime createdAt;

    public Notification_U() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
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
}