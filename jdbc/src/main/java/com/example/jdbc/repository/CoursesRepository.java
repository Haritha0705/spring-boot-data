package com.example.jdbc.repository;

import com.example.jdbc.model.Courses;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Repository
public class CoursesRepository {
    private final JdbcTemplate jdbcTemplate;
    public CoursesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int save(Courses entity) {
        String sql = "INSERT INTO courses(course_code, name, fee, instructor_id) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getCourseCode(),
                entity.getName(),
                entity.getFee(),
                entity.getInstructorId());
    }
    public List<Courses> findAll() {
        String sql = "SELECT id, course_code, name, fee, instructor_id, created_at, updated_at FROM courses ORDER BY id DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Courses entity = new Courses();
            entity.setId(rs.getInt("id"));
            entity.setCourseCode(rs.getInt("course_code"));
            entity.setName(rs.getString("name"));
            entity.setFee(rs.getFloat("fee"));
            entity.setInstructorId(rs.getInt("instructor_id"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            entity.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
            return entity;
        });
    }
    public Courses findById(Integer id) {
        String sql = "SELECT id, course_code, name, fee, instructor_id, created_at, updated_at FROM courses WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Courses entity = new Courses();
            entity.setId(rs.getInt("id"));
            entity.setCourseCode(rs.getInt("course_code"));
            entity.setName(rs.getString("name"));
            entity.setFee(rs.getFloat("fee"));
            entity.setInstructorId(rs.getInt("instructor_id"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            entity.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
            return entity;
        }, id);
    }
    public int update(Integer id, Courses entity) {
        String sql = "UPDATE courses SET course_code = ?, name = ?, fee = ?, instructor_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getCourseCode(),
                entity.getName(),
                entity.getFee(),
                entity.getInstructorId(), id);
    }
    public int delete(Integer id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}