package online.beautyskin.beauty.repository;

import online.beautyskin.beauty.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findByIsDeleteFalse();
    Blog findBlogById(long id);
}
