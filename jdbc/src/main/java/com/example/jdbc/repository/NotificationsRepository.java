package com.example.jdbc.repository;

import com.example.jdbc.model.Notification_U;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationsRepository {

    private final JdbcTemplate jdbcTemplate;

    public NotificationsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Notification_U entity) {
        String sql = "INSERT INTO notifications(student_id, title, message, is_read) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getIsRead());
    }

}