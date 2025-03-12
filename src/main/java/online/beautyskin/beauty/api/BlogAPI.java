package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.Blog;
import online.beautyskin.beauty.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/blog")
@SecurityRequirement(name = "api")
public class BlogAPI {

    List<Blog> blogs = new ArrayList<>();

    @Autowired
    BlogService blogService;

    @PostMapping("create")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity createBlog(@RequestBody@Valid Blog blog){
        Blog newBlog = blogService.createBlog(blog);
        return ResponseEntity.ok(newBlog);
    }

    @GetMapping("get")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity getAllBlog(){
        blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("getByDeleteIsFalse")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity getByDeleteIsFalse(){
        blogs = blogService.findByDeletedIsFalse();
        return ResponseEntity.ok(blogs);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity delete(@PathVariable long id){
        Blog blog = blogService.delete(id);
        return ResponseEntity.ok(blog);
    }

    @PostMapping("edit/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity edit(@RequestBody Blog blog,@PathVariable long id){
        Blog newBlog = blogService.edit(blog);
        return ResponseEntity.ok(newBlog);
    }
}
