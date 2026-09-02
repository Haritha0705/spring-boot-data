package com.example.jdbc.model;

import com.example.jdbc.enums.OrderStatus;
import java.time.LocalDateTime;

public class  Orders {

    private int id;

    private int studentId;

    private int courseId;

    private int paymentId;

    private float amount;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Orders(int id, int studentId, int courseId, int paymentId, float amount, OrderStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.paymentId = paymentId;
        this.amount = amount;
        this.status = status;
    }

    public Orders() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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
