package com.example.jdbc.controller;

import com.example.jdbc.dto.request.CoursesRequest;
import com.example.jdbc.dto.response.CoursesResponse;
import com.example.jdbc.service.CoursesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    private final CoursesService service;

    public CoursesController(CoursesService service) {
        this.service = service;
    }

    @PostMapping
    public int create(@RequestBody CoursesRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<CoursesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CoursesResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable Integer id, @RequestBody CoursesRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {
        return service.delete(id);
    }

}