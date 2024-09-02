package com.example.lap12.Repository;

import com.example.lap12.Model.Blog;
import com.example.lap12.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    Blog findBlogById(Integer id);

    List<Blog> findAllByUser(User user);

    Blog findBlogByTitle(String title);


}
