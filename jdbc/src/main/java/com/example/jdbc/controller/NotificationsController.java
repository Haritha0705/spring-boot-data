package com.example.jdbc.controller;

import com.example.jdbc.dto.request.NotificationsRequest;
import com.example.jdbc.dto.response.NotificationsResponse;
import com.example.jdbc.service.NotificationsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationsController {
    private final NotificationsService service;
    public NotificationsController(NotificationsService service) {
        this.service = service;
    }
    @PostMapping
    public int create(@RequestBody NotificationsRequest req) {
        return service.create(req);
    }
    @GetMapping
    public List<NotificationsResponse> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public NotificationsResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public int update(@PathVariable Integer id, @RequestBody NotificationsRequest req) {
        return service.update(id, req);
    }
    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}