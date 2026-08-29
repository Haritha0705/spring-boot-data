package com.example.jdbc.repository;

import com.example.jdbc.model.Contacts;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Repository
public class ContactsRepository {
    private final JdbcTemplate jdbcTemplate;
    public ContactsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int save(Contacts entity) {
        String sql = "INSERT INTO student_contacts(student_id, contact_type, contact_value) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getContactType(),
                entity.getContactValue());
    }
    public List<Contacts> findAll() {
        String sql = "SELECT id, student_id, contact_type, contact_value, created_at FROM student_contacts ORDER BY id DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Contacts entity = new Contacts();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setContactType(rs.getString("contact_type"));
            entity.setContactValue(rs.getString("contact_value"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        });
    }
    public Contacts findById(Integer id) {
        String sql = "SELECT id, student_id, contact_type, contact_value, created_at FROM student_contacts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Contacts entity = new Contacts();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setContactType(rs.getString("contact_type"));
            entity.setContactValue(rs.getString("contact_value"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        }, id);
    }
    public int update(Integer id, Contacts entity) {
        String sql = "UPDATE student_contacts SET student_id = ?, contact_type = ?, contact_value = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getContactType(),
                entity.getContactValue(), id);
    }
    public int delete(Integer id) {
        String sql = "DELETE FROM student_contacts WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}