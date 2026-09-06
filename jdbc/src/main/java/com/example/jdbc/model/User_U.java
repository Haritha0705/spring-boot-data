package com.example.jdbc.model;

import com.example.jdbc.enums.RoleType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = 'users')
public class User_U {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    private Long id;

    @Column(name = 'name')
    private String name;

    @Column(name = 'email')
    private String email;

    @Column(name = 'password')
    private String password;

    @Column(name = 'role')
    private RoleType role;

    @OneToMany(mappedBy = 'user')
    private List<Address_U> addresses = new ArrayList<>();

    @OneToOne(mappedBy = 'user')
    private Profile_U userProfiles;

    @OneToMany(mappedBy = 'user')
    private List<Payment_U> payments = new ArrayList<>();

    @OneToMany(mappedBy = 'user')
    private List<Notification_U> notifications = new ArrayList<>();

    @OneToMany(mappedBy = 'user')
    private List<Contacts_U> contacts = new ArrayList<>();

    @OneToMany(mappedBy = 'user')
    private List<Order_U> orders = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = 'enrollments',
            joinColumns = @JoinColumn(name = 'user_id'),
            inverseJoinColumns = @JoinColumn(name = 'course_id')
    )
    private Set<Course_U> courses = new HashSet<>();

    @Column(name = 'created_at')
    private LocalDateTime createdAt;

    @Column(name = 'updatedAt')
    private LocalDateTime updatedAt;

    public User_U() { }

    public User_U(String name, String email, String password, RoleType role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public List<Address_U> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address_U> addresses) {
        this.addresses = addresses;
    }

    public Profile_U getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Profile_U userProfiles) {
        this.userProfiles = userProfiles;
    }

    public List<Payment_U> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment_U> payments) {
        this.payments = payments;
    }

    public Set<Course_U> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course_U> courses) {
        this.courses = courses;
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