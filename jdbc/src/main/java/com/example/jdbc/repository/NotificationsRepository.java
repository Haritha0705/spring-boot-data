package com.example.jdbc.repository;

import com.example.jdbc.model.Notifications;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Repository
public class NotificationsRepository {
    private final JdbcTemplate jdbcTemplate;
    public NotificationsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int save(Notifications entity) {
        String sql = "INSERT INTO notifications(student_id, title, message, is_read) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getIsRead());
    }
    public List<Notifications> findAll() {
        String sql = "SELECT id, student_id, title, message, is_read, created_at FROM notifications ORDER BY id DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Notifications entity = new Notifications();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setTitle(rs.getString("title"));
            entity.setMessage(rs.getString("message"));
            entity.setIsRead(rs.getBoolean("is_read"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        });
    }
    public Notifications findById(Integer id) {
        String sql = "SELECT id, student_id, title, message, is_read, created_at FROM notifications WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Notifications entity = new Notifications();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setTitle(rs.getString("title"));
            entity.setMessage(rs.getString("message"));
            entity.setIsRead(rs.getBoolean("is_read"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        }, id);
    }
    public int update(Integer id, Notifications entity) {
        String sql = "UPDATE notifications SET student_id = ?, title = ?, message = ?, is_read = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getIsRead(), id);
    }
    public int delete(Integer id) {
        String sql = "DELETE FROM notifications WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}