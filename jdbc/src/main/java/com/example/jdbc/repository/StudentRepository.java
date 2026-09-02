package com.example.jdbc.repository;

import com.example.jdbc.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Student entity) {
        String sql = "INSERT INTO students(name, email, age) VALUES (?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                entity.getName(),
                entity.getEmail(),
                entity.getAge());
    }

    public List<Student> findAll() {
        String sql = "SELECT id, name, email, age, created_at, updated_at FROM students ORDER BY id DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Student entity = new Student();
            entity.setId(rs.getInt("id"));
            entity.setName(rs.getString("name"));
            entity.setEmail(rs.getString("email"));
            entity.setAge(rs.getInt("age"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            entity.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
            return entity;
        });
    }

    public Student findById(Long id) {
        String sql = "SELECT id, name, email, age, created_at, updated_at FROM students WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Student entity = new Student();
            entity.setId(rs.getInt("id"));
            entity.setName(rs.getString("name"));
            entity.setEmail(rs.getString("email"));
            entity.setAge(rs.getInt("age"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            entity.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
            return entity;
        }, id);
    }

    public int update(Long id, Student entity) {
        String sql = "UPDATE students SET name = ?, email = ?, age = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getName(),
                entity.getEmail(),
                entity.getAge(), id);
    }

    public int delete(Long id) {
        String sql = "DELETE FROM students WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}