package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    public List<Category> findByIsDeletedFalse();
    public Category findById(long id);
}
