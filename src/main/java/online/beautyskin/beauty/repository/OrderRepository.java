package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.enums.OrderStatusEnums;
import online.beautyskin.beauty.enums.PaymentStatusEnums;
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
                        "WHERE o.order_status = 'CONFIRMED' " +
                        "AND o.payment_status = 'PAID' " +
                        "GROUP BY year, month " +
                        "ORDER BY year DESC, month DESC", nativeQuery = true)
        List<Object[]> getMonthlyRevenue();

        @Query("SELECT o.user.fullName, SUM(o.totalPrice) AS totalSpent " +
                        "FROM Order o " +
                        "WHERE o.orderStatus = :orderStatus " +
                        "AND o.paymentStatus = :paymentStatus " +
                        "AND MONTH(o.orderDate) = MONTH(CURRENT_DATE) " +
                        "AND YEAR(o.orderDate) = YEAR(CURRENT_DATE) " +
                        "GROUP BY o.user.id, o.user.fullName " +
                        "ORDER BY totalSpent DESC " +
                        "LIMIT 3")
        List<Object[]> findTop3SpendingCustomers(@Param("orderStatus") OrderStatusEnums orderStatus,
                        @Param("paymentStatus") PaymentStatusEnums paymentStatus);

        @Query("SELECT COUNT(DISTINCT o.user.id) FROM Order o " +
                        "WHERE o.orderStatus = :status AND o.paymentStatus = :paymentStatus")
        long countCustomersWithDeliveredAndPaidOrders(@Param("status") OrderStatusEnums status,
                        @Param("paymentStatus") PaymentStatusEnums paymentStatus);

        @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Order o " +
                        "WHERE o.user.id = :customerId " +
                        "AND o.orderStatus = :status " +
                        "AND o.paymentStatus = :paymentStatus")
        double getTotalSpentByCustomer(@Param("customerId") Long customerId,
                        @Param("status") OrderStatusEnums status,
                        @Param("paymentStatus") PaymentStatusEnums paymentStatus);

        List<Order> findByOrderStatus(OrderStatusEnums orderStatus);

}
