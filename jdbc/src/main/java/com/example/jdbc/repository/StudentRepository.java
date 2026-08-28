package com.example.jdbc.repository;

import com.example.jdbc.dto.StudentResponse;
import com.example.jdbc.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private final JdbcTemplate jdbcTemplate;


    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Student student) {

        String sql = """
        INSERT INTO students(name, email, age, course)
        VALUES (?, ?, ?, ?)
        """;

        return jdbcTemplate.update(
                sql,
                student.getName(),
                student.getEmail(),
                student.getAge(),
                student.getCourse()
        );
    }

    public List<StudentResponse> findAll() {
        String sql = """
                SELECT id, name, email, age, course
                            FROM students
                            ORDER BY id DESC
                    """;
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new StudentResponse(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("course")
                )
        );
    }

    public StudentResponse findById(Long id) {

        String sql = """
        SELECT id, name, email, age, course
        FROM students
        WHERE id = ?
        """;

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> new StudentResponse(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("course")
                ),
                id
        );
    }

    public int update(Long id, Student student) {

        String sql = """
                UPDATE students
                SET name = ?,
                    email = ?,
                    age = ?,
                    course = ?
                WHERE id = ?
                """;

        return jdbcTemplate.update(
                sql,
                student.getName(),
                student.getEmail(),
                student.getAge(),
                student.getCourse(),
                id
        );
    }

    public int delete(Long id) {

        String sql = """
        DELETE FROM students
        WHERE id = ?
        """;

        return jdbcTemplate.update(
                sql,
                id
        );
    }

    public int count() {
        String sql = """
        SELECT COUNT(*)
        FROM students
        """;

        return jdbcTemplate.queryForObject(
                sql,
                Integer.class
        );
    }

    public boolean existsByEmail(String email) {
        String sql = """
        SELECT COUNT(*)
        FROM students
        WHERE email = ?
        """;

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                email
        );
        return count != null && count > 0;
    }

    public List<StudentResponse> findAll(
            String search,
            String course,
            Integer minAge,
            Integer maxAge
    ) {
        StringBuilder sql = new StringBuilder("""
        SELECT id, name, email, age, course
        FROM students
        WHERE 1 = 1
        """);

        List<Object> params = new ArrayList<>();

        //search
        if (search != null && !search.isBlank()) {
            sql.append("""
            AND (
                name ILIKE ?
                OR email ILIKE ?
                OR course ILIKE ?
            )
            """);

            String keyword = "%" + search + "%";

            params.add(keyword);
            params.add(keyword);
            params.add(keyword);
        }

        // Filter by course
        if (course != null && !course.isBlank()) {
            sql.append(" AND course = ? ");
            params.add(course);
        }

        // Filter by minimum age
        if (minAge != null) {

            sql.append(" AND age >= ? ");

            params.add(minAge);
        }

        // Filter by maximum age
        if (maxAge != null) {

            sql.append(" AND age <= ? ");

            params.add(maxAge);
        }

        sql.append(" ORDER BY id DESC");

        return jdbcTemplate.query(
                sql.toString(),
                (rs, rowNum) -> new StudentResponse(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age"),
                        rs.getString("course")
                ),
                params.toArray()
        );
    }
}
