package com.example.jdbc.service.Impl;

import com.example.jdbc.dto.request.EnrollmentsRequest;
import com.example.jdbc.dto.response.EnrollmentsResponse;
import com.example.jdbc.model.Enrollments;
import com.example.jdbc.mapper.EnrollmentsMapper;
import com.example.jdbc.repository.EnrollmentsRepository;
import com.example.jdbc.service.EnrollmentsService;
import com.example.jdbc.exception.AlreadyEnrolledException;
import com.example.jdbc.enums.EnrollmentStatus;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class EnrollmentsServiceImpl implements EnrollmentsService {

    private final EnrollmentsRepository enrollmentsRepository;
    private final EnrollmentsMapper mapper;

    public EnrollmentsServiceImpl(EnrollmentsRepository enrollmentsRepository, EnrollmentsMapper mapper) {
        this.enrollmentsRepository = enrollmentsRepository;
        this.mapper = mapper;
    }

    @Override
    public int create(EnrollmentsRequest request) {
        Enrollments entity = mapper.toEntity(request);
        return enrollmentsRepository.save(entity);
    }

    @Override
    public List<EnrollmentsResponse> getAll() {
        return enrollmentsRepository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public EnrollmentsResponse getById(Integer id) {
        return mapper.toResponse(enrollmentsRepository.findById(id));
    }

    @Override
    public int update(Integer id, EnrollmentsRequest request) {
        Enrollments entity = mapper.toEntity(request);
        return enrollmentsRepository.update(id, entity);
    }

    @Override
    public int delete(Integer id) {
        return enrollmentsRepository.delete(id);
    }
    
    @Override
    public void validateNotEnrolled(int studentId, int courseId) {
        Enrollments enrollment = enrollmentsRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (enrollment != null) throw new AlreadyEnrolledException("Student already enrolled in this course");
    }

    @Override
    public Enrollments enroll(int studentId, int courseId) {
        Enrollments enrollment = new Enrollments();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        enrollmentsRepository.save(enrollment);
        return enrollment;
    }
}