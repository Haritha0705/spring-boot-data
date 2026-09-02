package com.example.jdbc.repository;

import com.example.jdbc.enums.OrderStatus;
import com.example.jdbc.model.Orders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrdersRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrdersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class OrdersRowMapper implements RowMapper<Orders> {
        @Override
        public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
            Orders order = new Orders();
            order.setId(rs.getInt("id"));
            order.setStudentId(rs.getInt("student_id"));
            order.setCourseId(rs.getInt("course_id"));
            order.setPaymentId(rs.getInt("payment_id"));
            order.setAmount(rs.getFloat("amount"));
            order.setStatus(OrderStatus.valueOf(rs.getString("status")));
            order.setCreatedAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null);
            order.setUpdatedAt(rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null);
            return order;
        }
    }

    // =========================================================
    // CREATE ORDER
    // =========================================================

    public Orders save(Orders order) {

        String sql = """
                INSERT INTO orders (
                    student_id,
                    course_id,
                    amount,
                    status
                )
                VALUES (?, ?, ?, ?)
                RETURNING id
                """;

        Integer orderId = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                order.getStudentId(),
                order.getCourseId(),
                order.getAmount(),
                order.getStatus() != null ? order.getStatus().name() : null
        );

        order.setId(orderId);

        return order;
    }

    // =========================================================
    // FIND ORDER BY ID
    // =========================================================

    public Orders findById(Integer id) {

        String sql = """
                SELECT
                    id,
                    student_id,
                    course_id,
                    payment_id,
                    amount,
                    status,
                    created_at,
                    updated_at
                FROM orders
                WHERE id = ?
                """;

        List<Orders> orders = jdbcTemplate.query(
                sql,
                new OrdersRowMapper(),
                id
        );

        return orders.isEmpty()
                ? null
                : orders.get(0);
    }

    // =========================================================
    // FIND ORDER BY PAYMENT ID
    // =========================================================

    public Orders findByPaymentId(Integer paymentId) {

        String sql = """
                SELECT
                    id,
                    student_id,
                    course_id,
                    payment_id,
                    amount,
                    status,
                    created_at,
                    updated_at
                FROM orders
                WHERE payment_id = ?
                """;

        List<Orders> orders = jdbcTemplate.query(
                sql,
                new OrdersRowMapper(),
                paymentId
        );

        return orders.isEmpty()
                ? null
                : orders.get(0);
    }

    // =========================================================
    // UPDATE PAYMENT ID
    // =========================================================

    public void updatePaymentId(
            Integer orderId,
            Integer paymentId
    ) {

        String sql = """
                UPDATE orders
                SET
                    payment_id = ?,
                    updated_at = CURRENT_TIMESTAMP
                WHERE id = ?
                """;

        jdbcTemplate.update(
                sql,
                paymentId,
                orderId
        );
    }

    // =========================================================
    // UPDATE ORDER STATUS
    // =========================================================

    public void updateStatus(
            Integer orderId,
            String status
    ) {

        String sql = """
                UPDATE orders
                SET
                    status = ?,
                    updated_at = CURRENT_TIMESTAMP
                WHERE id = ?
                """;

        jdbcTemplate.update(
                sql,
                status,
                orderId
        );
    }

    // =========================================================
    // MARK ORDER AS PAID
    // =========================================================

    public void markPaid(Integer orderId) {

        String sql = """
                UPDATE orders
                SET
                    status = 'PAID',
                    updated_at = CURRENT_TIMESTAMP
                WHERE id = ?
                """;

        jdbcTemplate.update(sql, orderId);
    }

    // =========================================================
    // MARK ORDER AS FAILED
    // =========================================================

    public void markFailed(Integer orderId) {

        String sql = """
                UPDATE orders
                SET
                    status = 'FAILED',
                    updated_at = CURRENT_TIMESTAMP
                WHERE id = ?
                """;

        jdbcTemplate.update(sql, orderId);
    }

    // =========================================================
    // FIND ORDERS BY STUDENT
    // =========================================================

    public List<Orders> findByStudentId(
            Integer studentId
    ) {

        String sql = """
                SELECT
                    id,
                    student_id,
                    course_id,
                    payment_id,
                    amount,
                    status,
                    created_at,
                    updated_at
                FROM orders
                WHERE student_id = ?
                ORDER BY created_at DESC
                """;

        return jdbcTemplate.query(
                sql,
                new OrdersRowMapper(),
                studentId
        );
    }

    // =========================================================
    // FIND ORDERS BY COURSE
    // =========================================================

    public List<Orders> findByCourseId(
            Integer courseId
    ) {

        String sql = """
                SELECT
                    id,
                    student_id,
                    course_id,
                    payment_id,
                    amount,
                    status,
                    created_at,
                    updated_at
                FROM orders
                WHERE course_id = ?
                ORDER BY created_at DESC
                """;

        return jdbcTemplate.query(
                sql,
                new OrdersRowMapper(),
                courseId
        );
    }

}
