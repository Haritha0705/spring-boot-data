package com.example.jdbc.repository;

import com.example.jdbc.enums.EnrollmentStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public class EnrollmentsRepository {

    private final JdbcTemplate jdbcTemplate;

    public EnrollmentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Enrollment entity) {
        String sql = "INSERT INTO enrollments(student_id, course_id, enrollment_date, status) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getCourseId(),
                entity.getEnrollmentDate(),
                entity.getStatus());
    }
    
    public Enrollment findByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        String sql = "SELECT id, student_id, course_id, enrollment_date, status, created_at FROM enrollments WHERE student_id = ? AND course_id = ?";
        List<Enrollment> results = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Enrollment entity = new Enrollment();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setCourseId(rs.getInt("course_id"));
            entity.setEnrollmentDate(rs.getObject("enrollment_date", LocalDateTime.class));
            entity.setStatus(EnrollmentStatus.valueOf(rs.getString("status")));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        }, studentId, courseId);
        
        return results.isEmpty() ? null : results.getFirst();
    }
}