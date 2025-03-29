package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.enums.OrderStatusEnums;
import online.beautyskin.beauty.enums.PaymentStatusEnums;
import online.beautyskin.beauty.enums.RoleEnums;
import online.beautyskin.beauty.repository.OrderRepository;
import online.beautyskin.beauty.repository.ProductRepository;
import online.beautyskin.beauty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    public Map<String, Object> getDashboardStats(){
        Map<String,Object> stats = new HashMap<>();

        // Số lượng sản phẩm trong hệ thống
        long totalProducts = productRepository.count();
        stats.put("products", totalProducts);

        // Tổng doanh thu
        double totalRevenue = orderRepository.getTotalRevenue(OrderStatusEnums.CONFIRMED, PaymentStatusEnums.PAID);
        stats.put("revenue", totalRevenue);

        // Số lượng Order đã hoàn thành
        long completedOrders = orderRepository.countByStatus(OrderStatusEnums.CONFIRMED);
        stats.put("completedOrders", completedOrders);

//         Số lượng customer
        long totalCustomers = orderRepository.countCustomersWithDeliveredAndPaidOrders(OrderStatusEnums.CONFIRMED, PaymentStatusEnums.PAID);
        stats.put("customersWithOrders", totalCustomers);

        // top 3 khách hàng thân thiết trong 1 tháng
        List<Object[]> topCustomers = orderRepository.findTop3SpendingCustomers(OrderStatusEnums.CONFIRMED, PaymentStatusEnums.PAID);
        List<Map<String, Object>> topCustomersList = new ArrayList<>();

        for (Object[] customerData : topCustomers) {
            Map<String, Object> customerInfo = new HashMap<>();
            customerInfo.put("customerName", customerData[0]);
            customerInfo.put("totalSpent", customerData[1]);
            topCustomersList.add(customerInfo);
        }
        stats.put("topCustomers", topCustomersList);

        //top 5 sản phẩm bán chạy nhất
        List<Object[]> topProducts = productRepository.findTop5BestSellingProductsThisMonth(OrderStatusEnums.CONFIRMED, PaymentStatusEnums.PAID);
        List<Map<String, Object>> topProductsList = new ArrayList<>();

        for (Object[] productData : topProducts) {
            Map<String, Object> productInfo = new HashMap<>();
            productInfo.put("productName", productData[0]);
            productInfo.put("totalSold", productData[1]);
            topProductsList.add(productInfo);
        }
        stats.put("topProductsThisMonth", topProductsList);

        return stats;
    }

    public List<Map<String, Object>> getMonthlyRevenueStats() {
        List<Object[]> revenueData = orderRepository.getMonthlyRevenue();
        List<Map<String, Object>> revenueStats = new ArrayList<>();

        for (Object[] row : revenueData) {
            Map<String, Object> revenueInfo = new HashMap<>();
            revenueInfo.put("month", row[0]); // Tháng
            revenueInfo.put("year", row[1]);  // Năm
            revenueInfo.put("totalRevenue", row[2]); // Tổng doanh thu
            revenueStats.add(revenueInfo);
        }

        return revenueStats;
    }
}
