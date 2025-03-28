package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.StaffTask;
import online.beautyskin.beauty.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StaffTaskRepository extends JpaRepository<StaffTask, Long> {
    StaffTask findByOrder(Order order);

    List<StaffTask> findByStaff(User staff);

    @Query("SELECT COUNT(s) FROM StaffTask s WHERE s.staff.id = :staffId AND s.staffTaskEnums = 'DELIVERED'")
    long countCompletedOrdersByStaff(@Param("staffId") Long staffId);
}
