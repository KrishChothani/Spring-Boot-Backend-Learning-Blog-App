package com.cks_dev.demo.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cks_dev.demo.Excesption.ResourceNotFoundException;
import com.cks_dev.demo.models.Category;
import com.cks_dev.demo.models.Post;
import com.cks_dev.demo.models.User;
import com.cks_dev.demo.payloads.PostDto;
import com.cks_dev.demo.repository.CategoryRepo;
import com.cks_dev.demo.repository.PostRepo;
import com.cks_dev.demo.repository.UserRepo;
import com.cks_dev.demo.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post updatedPost = this.postRepo.save(post);

        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
       
        return null;
    }

    @Override
    public void deletePost(Integer postId) {
        // postRepo.deleteById(postId);
    }

    @Override
    public List<PostDto> getAllPost() {
        // return postRepo.findAll();
        return null;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        
        return null;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "Category Id", categoryId));
        List<Post> posts= this.postRepo.findByCategory(category);

        List<PostDto> postDtos =  posts.stream()
                                        .map((post) -> this.modelMapper.map(post, PostDto.class))
                                        .collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id ", userId));
        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream()
                                        .map((post) -> this.modelMapper.map(post, PostDto.class))
                                        .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        
        return null;
    }
}
