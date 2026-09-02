package com.example.jdbc.service.Impl;

import com.example.jdbc.model.Courses;
import com.example.jdbc.model.Orders;
import com.example.jdbc.repository.OrdersRepository;
import com.example.jdbc.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;

    public OrderServiceImpl(
            OrdersRepository ordersRepository
    ) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Orders createPendingOrder(
            Integer studentId,
            Courses course
    ) {

        Orders order = new Orders();

        order.setStudentId(studentId);
        order.setCourseId(course.getId());
        order.setAmount(course.getFee());
        order.setStatus(com.example.jdbc.enums.OrderStatus.PENDING);

        return ordersRepository.save(order);
    }

    @Override
    public void markPaid(Integer orderId) {

        ordersRepository.updateStatus(
                orderId,
                "PAID"
        );
    }

    @Override
    public void markFailed(Integer orderId) {

        ordersRepository.updateStatus(
                orderId,
                "FAILED"
        );
    }

}
