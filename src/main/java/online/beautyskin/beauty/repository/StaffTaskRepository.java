package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.StaffTask;
import online.beautyskin.beauty.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffTaskRepository extends JpaRepository<StaffTask, Long> {
    StaffTask findByOrder(Order order);

    List<StaffTask> findByStaff(User staff);
}
