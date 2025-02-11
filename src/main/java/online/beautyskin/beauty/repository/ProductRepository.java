package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    public Product findByName(String name);
    public List<Product> findByIsDeletedFalse();
    public Product findById(long id);
}
