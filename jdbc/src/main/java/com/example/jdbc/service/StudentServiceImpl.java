package com.example.jdbc.service;

import com.example.jdbc.dto.StudentRequest;
import com.example.jdbc.dto.StudentResponse;
import com.example.jdbc.model.Student;
import com.example.jdbc.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public int createStudent(StudentRequest request) {
        Student student = new Student();

        student.setName(request.name());
        student.setEmail(request.email());
        student.setAge(request.age());
        student.setCourse(request.course());

        return repository.save(student);
    }
    @Override
    public List<StudentResponse> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        return repository.findById(id);
    }

    @Override
    public int updateStudent(Long id, StudentRequest request) {
        Student student = new Student();
        student.setName(request.name());
        student.setEmail(request.email());
        student.setAge(request.age());
        student.setCourse(request.course());
        return repository.update(id, student);
    }

    @Override
    public int deleteStudent(Long id) {
        return repository.delete(id);
    }

    @Override
    public int countStudents() {
        return repository.count();
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public List<StudentResponse> findFilteredStudents(String search, String course, Integer minAge, Integer maxAge) {
        return repository.findAll(search, course, minAge, maxAge);
    }
}
