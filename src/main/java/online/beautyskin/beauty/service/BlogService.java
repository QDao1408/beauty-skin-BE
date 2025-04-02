package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Blog;
import online.beautyskin.beauty.entity.request.BlogRequest;
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
    public List<Blog> findByDeletedIsFalse(){
        return blogRepository.findByIsDeletedFalse();
    }
    //delete
    public Blog delete(long id){
        Blog blog = blogRepository.findBlogById(id);
        blog.setDeleted(true);
        return blogRepository.save(blog);
    }

    public Blog getById(long id) {
        return blogRepository.findBlogById(id);
    }

    public Blog edit(BlogRequest request, long id) {
        Blog blog = blogRepository.findBlogById(id);

        blog.setAuthor(request.getAuthor());
        blog.setTitle(request.getTitle());
        blog.setContent(request.getContent());
        blog.setImage(request.getImage());;
        blog.setSlug(request.getSlug());
        blog.setTag(request.getTag());
        blogRepository.save(blog);
        return blog;

    }
}
