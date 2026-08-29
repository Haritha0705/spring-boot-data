package com.example.jdbc.controller;

import com.example.jdbc.dto.request.StudentRequest;
import com.example.jdbc.dto.response.StudentResponse;
import com.example.jdbc.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<StudentResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable Long id, @RequestBody StudentRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Long id) {
        return service.delete(id);
    }

}