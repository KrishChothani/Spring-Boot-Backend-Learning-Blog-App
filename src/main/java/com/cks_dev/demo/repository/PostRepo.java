package com.cks_dev.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cks_dev.demo.models.Category;
import com.cks_dev.demo.models.Post;
import com.cks_dev.demo.models.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
