package com.example.jdbc.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
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

    public Student findById(int id) {
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

    public int update(int id, Student entity) {
        String sql = "UPDATE students SET name = ?, email = ?, age = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getName(),
                entity.getEmail(),
                entity.getAge(), id);
    }

    public int delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}