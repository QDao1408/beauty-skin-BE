package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    List<Manager> findByIsDeleteFalse();
    Manager findByName(String name);
    Manager findById(long id);
}
