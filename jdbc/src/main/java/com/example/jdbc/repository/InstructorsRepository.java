package com.example.jdbc.repository;

import com.example.jdbc.model.Instructors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Repository
public class InstructorsRepository {
    private final JdbcTemplate jdbcTemplate;
    public InstructorsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int save(Instructors entity) {
        String sql = "INSERT INTO instructors(name, email, manager_id) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getName(),
                entity.getEmail(),
                entity.getManagerId());
    }
    public List<Instructors> findAll() {
        String sql = "SELECT id, name, email, manager_id, created_at FROM instructors ORDER BY id DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Instructors entity = new Instructors();
            entity.setId(rs.getInt("id"));
            entity.setName(rs.getString("name"));
            entity.setEmail(rs.getString("email"));
            entity.setManagerId(rs.getInt("manager_id"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        });
    }
    public Instructors findById(Integer id) {
        String sql = "SELECT id, name, email, manager_id, created_at FROM instructors WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Instructors entity = new Instructors();
            entity.setId(rs.getInt("id"));
            entity.setName(rs.getString("name"));
            entity.setEmail(rs.getString("email"));
            entity.setManagerId(rs.getInt("manager_id"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        }, id);
    }
    public int update(Integer id, Instructors entity) {
        String sql = "UPDATE instructors SET name = ?, email = ?, manager_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getName(),
                entity.getEmail(),
                entity.getManagerId(), id);
    }
    public int delete(Integer id) {
        String sql = "DELETE FROM instructors WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}