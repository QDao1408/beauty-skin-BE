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
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    public Map<String, Object> getDashboardStats(){
        Map<String,Object> stats = new HashMap<>();

        // Số lượng sản phẩm trong hệ thống
        long totalProducts = productRepository.count();
        stats.put("products", totalProducts);

        // Tổng doanh thu
        double totalRevenue = orderRepository.getTotalRevenue(OrderStatusEnums.DELIVERED, PaymentStatusEnums.PAID);
        stats.put("revenue", totalRevenue);

        // Số lượng Order đã hoàn thành
        long completedOrders = orderRepository.countByStatus(OrderStatusEnums.DELIVERED);
        stats.put("completedOrders", completedOrders);

        // Số lượng customer
        long totalCustomers = userRepository.countByRole(RoleEnums.USER);
        stats.put("customers", totalCustomers);

        //top 5 sản phẩm bán chạy nhất
        List<Object[]> topProducts = productRepository.findTop5BestSellingProduct();

        List<Map<String, Object>> topProductsList = new ArrayList<>();
        int limit = Math.min(topProducts.size(), 5);

        for (int i = 0; i < limit; i++) {
            Object[] productData = topProducts.get(i);
            Map<String, Object> productInfo = new HashMap<>();
            productInfo.put("productName", productData[0]);
            productInfo.put("totalSold", productData[1]);
            topProductsList.add(productInfo);
        }

        stats.put("topProducts", topProductsList);

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
