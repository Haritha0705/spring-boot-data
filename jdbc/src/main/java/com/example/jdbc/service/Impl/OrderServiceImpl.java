package com.example.jdbc.service.Impl;

import com.example.jdbc.model.Course_U;
import com.example.jdbc.model.Order_U;
import com.example.jdbc.repository.OrdersRepository;
import com.example.jdbc.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;

    public OrderServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Order_U createPendingOrder(Integer studentId, Course_U course) {

        Order_U order = new Order_U();

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
