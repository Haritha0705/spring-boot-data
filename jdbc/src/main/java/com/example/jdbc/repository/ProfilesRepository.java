package com.example.jdbc.repository;

import com.example.jdbc.model.Profile_U;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public class ProfilesRepository {
    private final JdbcTemplate jdbcTemplate;
    public ProfilesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int save(Profile_U entity) {
        String sql = "INSERT INTO student_profiles(student_id, date_of_birth, gender, bio) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getDateOfBirth(),
                entity.getGender(),
                entity.getBio());
    }
    public List<Profile_U> findAll() {
        String sql = "SELECT id, student_id, date_of_birth, gender, bio, created_at FROM student_profiles ORDER BY id DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Profile_U entity = new Profile_U();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setDateOfBirth(rs.getObject("date_of_birth", LocalDateTime.class));
            entity.setGender(rs.getString("gender"));
            entity.setBio(rs.getString("bio"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        });
    }
    public Profile_U findById(Integer id) {
        String sql = "SELECT id, student_id, date_of_birth, gender, bio, created_at FROM student_profiles WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Profile_U entity = new Profile_U();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setDateOfBirth(rs.getObject("date_of_birth", LocalDateTime.class));
            entity.setGender(rs.getString("gender"));
            entity.setBio(rs.getString("bio"));
            entity.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
            return entity;
        }, id);
    }
    public int update(Integer id, Profile_U entity) {
        String sql = "UPDATE student_profiles SET student_id = ?, date_of_birth = ?, gender = ?, bio = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getDateOfBirth(),
                entity.getGender(),
                entity.getBio(), id);
    }
    public int delete(Integer id) {
        String sql = "DELETE FROM student_profiles WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}