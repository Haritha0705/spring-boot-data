package com.example.jdbc.controller;

import com.example.jdbc.dto.request.InstructorsRequest;
import com.example.jdbc.dto.response.InstructorsResponse;
import com.example.jdbc.service.InstructorsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorsController {

    private final InstructorsService service;

    public InstructorsController(InstructorsService service) {
        this.service = service;
    }

    @PostMapping
    public int create(@RequestBody InstructorsRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<InstructorsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public InstructorsResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable Integer id, @RequestBody InstructorsRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {
        return service.delete(id);
    }

}