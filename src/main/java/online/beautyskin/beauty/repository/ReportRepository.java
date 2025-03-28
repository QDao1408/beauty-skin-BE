package online.beautyskin.beauty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long>{
    List<Report> findByOrder(Order order);
}
