package com.example.lap12.Service;

import com.example.lap12.Api.ApiException;
import com.example.lap12.Model.Blog;
import com.example.lap12.Model.User;
import com.example.lap12.Repository.AuthRepository;
import com.example.lap12.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getMyBlogs(Integer id) {
        User user=authRepository.findUserById(id);

        return blogRepository.findAllByUser(user);
    }

    public void addBlog(Integer auth_id, Blog blog) {
        User user=authRepository.findUserById(auth_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer auth_id,Integer blog_id, Blog blog) {
        User user=authRepository.findUserById(auth_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        Blog blog1=blogRepository.findBlogById(blog_id);
        if(blog1==null){
            throw new ApiException("blog not found");
        }else if(blog1.getUser().getId()!=auth_id){
            throw new ApiException("sorry you dont have authority");
        }
        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blog1.setUser(user);
        blogRepository.save(blog1);
    }
    public void deleteBlog(Integer auth_id,Integer blog_id) {
        User user=authRepository.findUserById(auth_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        Blog blog1=blogRepository.findBlogById(blog_id);
        if(blog1==null){
            throw new ApiException("blog not found");
        }
        else if(blog1.getUser().getId()!=auth_id){
            throw new ApiException("sorry you dont have authority");
        }
        blogRepository.delete(blog1);
    }

    public Blog getBlogById(Integer auth_id,Integer blog_id) {
        User user=authRepository.findUserById(auth_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        Blog blog1=blogRepository.findBlogById(blog_id);
        if(blog1==null){
            throw new ApiException("blog not found");
        }
        else if(blog1.getUser().getId()!=auth_id){
            throw new ApiException("sorry you dont have authority");
        }
        return blog1;
    }

    public Blog getBlogByTitle(Integer auth_id,String title) {
        User user=authRepository.findUserById(auth_id);
        if(user==null){
            throw new ApiException("user not found");
        }
        Blog blog1=blogRepository.findBlogByTitle(title);
        if(blog1==null){
            throw new ApiException("blog not found");
        }
        else if(blog1.getUser().getId()!=auth_id){
            throw new ApiException("sorry you dont have authority");
        }
        return blog1;
    }

    public Set<Blog> getUserBlogs(Integer auth_id) {
      User user1=authRepository.findUserById(auth_id);
      if(user1==null){
          throw new ApiException("user not found");
      }
      return user1.getBlogs();
    }


}
