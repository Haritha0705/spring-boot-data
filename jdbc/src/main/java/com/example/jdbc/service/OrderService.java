package com.example.jdbc.service;

import com.example.jdbc.model.Courses;
import com.example.jdbc.model.Orders;

public interface OrderService {

    Orders createPendingOrder(Integer studentId, Courses course);

    void markPaid(Integer orderId);

    void markFailed(Integer orderId);

}
