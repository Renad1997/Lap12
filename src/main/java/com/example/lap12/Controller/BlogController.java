package com.example.lap12.Controller;

import com.example.lap12.Model.Blog;
import com.example.lap12.Model.User;
import com.example.lap12.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get-all")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

    @GetMapping("/get-my")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(blogService.getMyBlogs(user.getId()));
    }


    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user,@RequestBody @Valid Blog blog){
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("Blog added");
    }

    @PutMapping("/update/{blog_id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @PathVariable Integer blog_id,@RequestBody @Valid Blog blog){
        blogService.updateBlog(user.getId(), blog_id, blog);
        return ResponseEntity.status(200).body("Blog updated");
    }

    @DeleteMapping("/delete/{blog_id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer blog_id){
        blogService.deleteBlog(user.getId(),blog_id);
        return ResponseEntity.status(200).body("Blog deleted");
    }
    @GetMapping("/blogs/{blog_id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user, @PathVariable Integer blog_id){
        return ResponseEntity.status(200).body(blogService.getBlogById(user.getId(),blog_id));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal User user, @PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(user.getId(),title));
    }

    @GetMapping("/user/{auth_id}")
    public ResponseEntity getUserBlogs(@AuthenticationPrincipal User user,@PathVariable Integer auth_id){
        return ResponseEntity.status(200).body(blogService.getUserBlogs(auth_id));
    }
}
