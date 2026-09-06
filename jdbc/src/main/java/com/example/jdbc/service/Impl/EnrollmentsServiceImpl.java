package com.example.jdbc.service.Impl;

import com.example.jdbc.model.Enrollment_U;
import com.example.jdbc.repository.EnrollmentsRepository;
import com.example.jdbc.service.EnrollmentsService;
import com.example.jdbc.exception.AlreadyEnrolledException;
import com.example.jdbc.enums.EnrollmentStatus;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentsServiceImpl implements EnrollmentsService {

    private final EnrollmentsRepository enrollmentsRepository;

    public EnrollmentsServiceImpl(EnrollmentsRepository enrollmentsRepository) {
        this.enrollmentsRepository = enrollmentsRepository;
    }
    
    @Override
    public void validateNotEnrolled(int studentId, int courseId) {
        Enrollment_U enrollment = enrollmentsRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (enrollment != null) throw new AlreadyEnrolledException("Student already enrolled in this course");
    }

    @Override
    public Enrollment_U enroll(int studentId, int courseId) {
        Enrollment_U enrollment = new Enrollment_U();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        enrollment.setStatus(EnrollmentStatus.ACTIVE);
        enrollmentsRepository.save(enrollment);
        return enrollment;
    }

}