package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.enums.OrderStatusEnums;
import online.beautyskin.beauty.enums.PaymentStatusEnums;
import online.beautyskin.beauty.enums.RoleEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(long id);

    Order findOrderById(long id);

    List<Order> findOrderByUserId(long id);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.orderStatus = :status")
    long countByStatus(@Param("status") OrderStatusEnums status);

    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Order o WHERE o.orderStatus = :orderStatus AND o.paymentStatus = :paymentStatus")
    double getTotalRevenue(@Param("orderStatus") OrderStatusEnums orderStatus,
                           @Param("paymentStatus") PaymentStatusEnums paymentStatus);

    @Query(value = "SELECT EXTRACT(MONTH FROM o.order_date) AS month, " +
            "EXTRACT(YEAR FROM o.order_date) AS year, " +
            "SUM(o.total_price) AS totalRevenue " +
            "FROM orders o " +
            "WHERE o.order_status = 'DELIVERED' " +
            "AND o.payment_status = 'PAID' " +
            "GROUP BY year, month " +
            "ORDER BY year DESC, month DESC",
            nativeQuery = true)
    List<Object[]> getMonthlyRevenue();
}
