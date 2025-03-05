package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findByIsDeletedFalse();
    Brand findById(long id);
    Brand findByName(String brandName);
}
