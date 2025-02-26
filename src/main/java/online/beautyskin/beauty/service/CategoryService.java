package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Category;
import online.beautyskin.beauty.entity.request.CategoryRequest;
import online.beautyskin.beauty.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findByIsDeletedFalse();
    }

    public Category findById(long id) {
        return categoryRepository.findById(id);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public Category create(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    public Category delete(long id) {
        Category cate = categoryRepository.findById(id);
        cate.setDeleted(true);
        return categoryRepository.save(cate);
    }

    public Category updateCategory(long id, CategoryRequest categoryRequest) {
        Category cate = categoryRepository.findById(id);
        cate.setName(categoryRequest.getName());
        return categoryRepository.save(cate);
    }
}
