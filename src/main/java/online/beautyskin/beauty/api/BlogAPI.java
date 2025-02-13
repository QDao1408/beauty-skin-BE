package online.beautyskin.beauty.api;

import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.Blog;
import online.beautyskin.beauty.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogAPI {

    List<Blog> blogs = new ArrayList<>();

    @Autowired
    BlogService blogService;

    @PostMapping("create")
    public ResponseEntity createBlog(@RequestBody@Valid Blog blog){
        Blog newBlog = blogService.createBlog(blog);
        return ResponseEntity.ok(newBlog);
    }

    @GetMapping("get")
    public ResponseEntity getAllBlog(){
        blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("getByDeleteIsFalse")
    public ResponseEntity getByDeleteIsFalse(){
        blogs = blogService.findByDeleteIsFalse();
        return ResponseEntity.ok(blogs);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable long id){
        Blog blog = blogService.delete(id);
        return ResponseEntity.ok(blog);
    }

    @PostMapping("edit/{id}")
    public ResponseEntity edit(@RequestBody Blog blog,@PathVariable long id){
        Blog newBlog = blogService.edit(blog);
        return ResponseEntity.ok(newBlog);
    }
}
