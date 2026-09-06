package com.example.jdbc.repository;

import com.example.jdbc.model.Payment_U;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentsRepository {

    private final JdbcTemplate jdbcTemplate;

    public PaymentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Payment_U entity) {
        String sql = "INSERT INTO payments(student_id, course_id, amount, payment_method, payment_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getStudentId(),
                entity.getCourseId(),
                entity.getAmount(),
                entity.getPaymentMethod() != null ? entity.getPaymentMethod().name() : null,
                entity.getPaymentDate(),
                entity.getStatus() != null ? entity.getStatus().name() : null);
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