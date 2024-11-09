package com.cks_dev.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cks_dev.demo.payloads.PostDto;
import com.cks_dev.demo.payloads.PostResponse;

@Service
public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);


    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber , Integer pageSize);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);

}
