package com.example.jdbc.repository;

import com.example.jdbc.enums.PaymentMethod;
import com.example.jdbc.enums.PaymentStatus;
import com.example.jdbc.model.Payments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Repository
public class PaymentsRepository {

    private final JdbcTemplate jdbcTemplate;

    public PaymentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int save(Payments entity) {
        String sql = "INSERT INTO payments(student_id, course_id, amount, payment_method, payment_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getCourseId(),
                entity.getAmount(),
                entity.getPaymentMethod() != null ? entity.getPaymentMethod().name() : null,
                entity.getPaymentDate(),
                entity.getStatus() != null ? entity.getStatus().name() : null);
    }
    public List<Payments> findAll() {
        String sql = "SELECT id, student_id, amount, payment_method, payment_date, status FROM payments ORDER BY id DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Payments entity = new Payments();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setAmount(rs.getFloat("amount"));
            entity.setPaymentMethod(PaymentMethod.valueOf(rs.getString("payment_method")));
            entity.setPaymentDate(rs.getObject("payment_date", LocalDateTime.class));
            entity.setStatus(PaymentStatus.valueOf(rs.getString("status")));
            return entity;
        });
    }
    public Payments findById(Integer id) {
        String sql = "SELECT id, student_id, amount, payment_method, payment_date, status FROM payments WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Payments entity = new Payments();
            entity.setId(rs.getInt("id"));
            entity.setStudentId(rs.getInt("student_id"));
            entity.setAmount(rs.getFloat("amount"));
            entity.setPaymentMethod(PaymentMethod.valueOf(rs.getString("payment_method")));
            entity.setPaymentDate(rs.getObject("payment_date", LocalDateTime.class));
            entity.setStatus(PaymentStatus.valueOf(rs.getString("status")));
            return entity;
        }, id);
    }
    public int update(Integer id, Payments entity) {
        String sql = "UPDATE payments SET student_id = ?, amount = ?, payment_method = ?, payment_date = ?, status = ? WHERE id = ?";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getAmount(),
                entity.getPaymentMethod(),
                entity.getPaymentDate(),
                entity.getStatus(), id);
    }
    public int delete(Integer id) {
        String sql = "DELETE FROM payments WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // UPDATE PAYMENT TO SUCCESS
    public void markSuccessful(int paymentId, String transactionId) {
        String sql = """
                UPDATE payments
                SET 
                    status = 'SUCCESS',
                    transaction_id = ?
                WHERE id = ?
                """;
        jdbcTemplate.update(
                sql,
                transactionId,
                paymentId
        );
    }

    // UPDATE PAYMENT TO FAILED
    public void markFailed(int paymentId,
                           String reason) {
        String sql = """
                UPDATE payments
                SET 
                    status = 'FAILED'
                WHERE id = ?
                """;
        jdbcTemplate.update(
                sql,
                paymentId
        );
    }
}