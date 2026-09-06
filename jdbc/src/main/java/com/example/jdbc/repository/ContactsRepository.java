package com.example.jdbc.repository;

import com.example.jdbc.model.Contacts_U;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactsRepository {

    private final JdbcTemplate jdbcTemplate;

    public ContactsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Contacts_U entity) {
        String sql = "INSERT INTO student_contacts(student_id, contact_type, contact_value) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getContactType(),
                entity.getContactValue());
    }

    public int update(Integer id, Contacts_U entity) {
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