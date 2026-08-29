package com.example.jdbc.service;

import com.example.jdbc.dto.request.PaymentsRequest;
import com.example.jdbc.dto.response.PaymentsResponse;
import java.util.List;

public interface PaymentsService {
    int create(PaymentsRequest request);
    List<PaymentsResponse> getAll();
    PaymentsResponse getById(Integer id);
    int update(Integer id, PaymentsRequest request);
    int delete(Integer id);
}