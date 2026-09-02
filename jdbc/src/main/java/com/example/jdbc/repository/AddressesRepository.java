package com.example.jdbc.repository;

import com.example.jdbc.enums.AddressType;
import com.example.jdbc.model.Addresses;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

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
    public List<Addresses> findAll() {
        String sql = "SELECT id, student_id, address_line, city, country, address_type, created_at FROM addresses ORDER BY id DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Addresses entity = new Addresses();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setAddressLine(rs.getString("address_line"));
            entity.setCity(rs.getString("city"));
            entity.setCountry(rs.getString("country"));
            entity.setAddressType(AddressType.valueOf(rs.getString("address_type")));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        });
    }
    public Addresses findById(Integer id) {
        String sql = "SELECT id, student_id, address_line, city, country, address_type, created_at FROM addresses WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Addresses entity = new Addresses();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setAddressLine(rs.getString("address_line"));
            entity.setCity(rs.getString("city"));
            entity.setCountry(rs.getString("country"));
            entity.setAddressType(AddressType.valueOf(rs.getString("address_type")));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        }, id);
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