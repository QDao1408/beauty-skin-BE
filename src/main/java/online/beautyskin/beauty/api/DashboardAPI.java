package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@SecurityRequirement(name = "api")
public class DashboardAPI {
    @Autowired
    DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity getDashboardStats() {
        Map<String,Object> stats = dashboardService.getDashboardStats();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/monthly-revenue")
    public List<Map<String, Object>> getMonthlyRevenue() {
        return dashboardService.getMonthlyRevenueStats();
    }
}
