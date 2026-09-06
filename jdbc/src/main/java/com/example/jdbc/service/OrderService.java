package com.example.jdbc.service;

import com.example.jdbc.model.Course_U;
import com.example.jdbc.model.Order_U;

public interface OrderService {

    Order_U createPendingOrder(Integer studentId, Course_U course);

    void markPaid(Integer orderId);

    void markFailed(Integer orderId);

}
