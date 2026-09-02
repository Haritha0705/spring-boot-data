package com.example.jdbc.controller;

import com.example.jdbc.dto.request.PaymentsRequest;
import com.example.jdbc.dto.response.PaymentsResponse;
import com.example.jdbc.service.PaymentsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    private final PaymentsService service;

    public PaymentsController(PaymentsService service) {
        this.service = service;
    }

    @PostMapping
    public int create(@RequestBody PaymentsRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<PaymentsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PaymentsResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable Integer id, @RequestBody PaymentsRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {
        return service.delete(id);
    }

}