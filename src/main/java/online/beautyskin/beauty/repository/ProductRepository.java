package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Brand;
import online.beautyskin.beauty.entity.Category;
import online.beautyskin.beauty.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    public Product findByName(String name);
    public List<Product> findByIsDeletedFalse();
    public Product findById(long id);
    List<Product> findByCategory(Category category);
    List<Product> findByBrand(Brand brand);

    @Query("select p " +
            "from Product p " +
            "join  p.skinTypes s " +
            "where s.id = :id")
    List<Product> findBySkinTypesIdAndIsDeletedFalse(long id);

}
