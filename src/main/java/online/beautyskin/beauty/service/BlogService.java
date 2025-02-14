package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Blog;
import online.beautyskin.beauty.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogService {



    @Autowired
    BlogRepository blogRepository;

    //create
    public Blog createBlog(Blog blog){
        return blogRepository.save(blog);
    }

    //getAll
    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }
    //findByDeleteIsFalse
    public List<Blog> findByDeleteIsFalse(){
        return blogRepository.findByIsDeleteFalse();
    }
    //delete
    public Blog delete(long id){
        Blog blog = blogRepository.findBlogById(id);
        blog.setDelete(true);
        return blogRepository.save(blog);
    }
    //edit
    public Blog edit(Blog blog){
        return blogRepository.save(blog);
    }
}
