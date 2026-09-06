package com.example.jdbc.model;

import com.example.jdbc.enums.PaymentMethod;
import com.example.jdbc.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = 'payments')
public class Payment_U {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    private Long id;

    @Column(name = 'user_id')
    private Long user_id;

    @Column(name = 'course_id')
    private Long courseId;

    @Column(name = 'amount')
    private float amount;

    @Column(name = 'payment_method')
    private PaymentMethod paymentMethod;

    @Column(name = 'payment_date')
    private LocalDateTime paymentDate;

    @Column(name = 'status')
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn('user_id')
    private User_U user;

    @Column(name = 'created_at')
    private LocalDateTime createdAt;

    public Payment_U() {}

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
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