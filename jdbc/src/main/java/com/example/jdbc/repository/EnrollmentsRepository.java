package com.example.jdbc.repository;

import com.example.jdbc.enums.EnrollmentStatus;
import com.example.jdbc.model.Enrollments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Repository
public class EnrollmentsRepository {
    private final JdbcTemplate jdbcTemplate;
    public EnrollmentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int save(Enrollments entity) {
        String sql = "INSERT INTO enrollments(student_id, course_id, enrollment_date, status) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getCourseId(),
                entity.getEnrollmentDate(),
                entity.getStatus());
    }
    public List<Enrollments> findAll() {
        String sql = "SELECT id, student_id, course_id, enrollment_date, status, created_at FROM enrollments ORDER BY id DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Enrollments entity = new Enrollments();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setCourseId(rs.getInt("course_id"));
            entity.setEnrollmentDate(rs.getObject("enrollment_date", LocalDateTime.class));
            entity.setStatus(EnrollmentStatus.valueOf(rs.getString("status")));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        });
    }
    public Enrollments findById(Integer id) {
        String sql = "SELECT id, student_id, course_id, enrollment_date, status, created_at FROM enrollments WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Enrollments entity = new Enrollments();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setCourseId(rs.getInt("course_id"));
            entity.setEnrollmentDate(rs.getObject("enrollment_date", LocalDateTime.class));
            entity.setStatus(EnrollmentStatus.valueOf(rs.getString("status")));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        }, id);
    }
    public int update(Integer id, Enrollments entity) {
        String sql = "UPDATE enrollments SET student_id = ?, course_id = ?, enrollment_date = ?, status = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getCourseId(),
                entity.getEnrollmentDate(),
                entity.getStatus(), id);
    }
    public int delete(Integer id) {
        String sql = "DELETE FROM enrollments WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
    
    public Enrollments findByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        String sql = "SELECT id, student_id, course_id, enrollment_date, status, created_at FROM enrollments WHERE student_id = ? AND course_id = ?";
        List<Enrollments> results = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Enrollments entity = new Enrollments();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setCourseId(rs.getInt("course_id"));
            entity.setEnrollmentDate(rs.getObject("enrollment_date", LocalDateTime.class));
            entity.setStatus(EnrollmentStatus.valueOf(rs.getString("status")));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        }, studentId, courseId);
        
        return results.isEmpty() ? null : results.get(0);
    }
}