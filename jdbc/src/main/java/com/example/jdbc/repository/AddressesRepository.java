package com.example.jdbc.repository;

import com.example.jdbc.model.Addresses;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressesRepository {

    private final JdbcTemplate jdbcTemplate;

    public AddressesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Addresses entity) {
        String sql = "INSERT INTO addresses(student_id, address_line, city, country, address_type) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                entity.getStudentId(),
                entity.getAddressLine(),
                entity.getCity(),
                entity.getCountry(),
                entity.getAddressType());
    }

    public int update(Integer id, Addresses entity) {
        String sql = "UPDATE addresses SET student_id = ?, address_line = ?, city = ?, country = ?, address_type = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getAddressLine(),
                entity.getCity(),
                entity.getCountry(),
                entity.getAddressType(), id);
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM addresses WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}