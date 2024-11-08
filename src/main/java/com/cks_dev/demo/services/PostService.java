package com.cks_dev.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cks_dev.demo.models.Post;
import com.cks_dev.demo.payloads.PostDto;

@Service
public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);


    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getAllPost();

    PostDto getPostById(Integer postId);

    List<PostDto> getAllPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);

}
