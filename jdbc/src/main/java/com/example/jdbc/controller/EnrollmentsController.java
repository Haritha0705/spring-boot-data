package com.example.jdbc.controller;

import com.example.jdbc.dto.request.EnrollmentsRequest;
import com.example.jdbc.dto.response.EnrollmentsResponse;
import com.example.jdbc.service.EnrollmentsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentsController {
    private final EnrollmentsService service;
    public EnrollmentsController(EnrollmentsService service) {
        this.service = service;
    }
    @PostMapping
    public int create(@RequestBody EnrollmentsRequest req) {
        return service.create(req);
    }
    @GetMapping
    public List<EnrollmentsResponse> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public EnrollmentsResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public int update(@PathVariable Integer id, @RequestBody EnrollmentsRequest req) {
        return service.update(id, req);
    }
    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}