package com.cks_dev.demo.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cks_dev.demo.Excesption.ResourceNotFoundException;
import com.cks_dev.demo.models.Category;
import com.cks_dev.demo.models.Post;
import com.cks_dev.demo.models.User;
import com.cks_dev.demo.payloads.PostDto;
import com.cks_dev.demo.payloads.PostResponse;
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
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "Post Id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "Post Id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber , Integer pageSize) {

       Pageable p = PageRequest.of(pageNumber, pageSize);

        Page<Post> pagePosts = this.postRepo.findAll(p);
        List<Post> posts = pagePosts.getContent();

        List<PostDto> postDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse response = new PostResponse();
        response.setContent(postDto);
        response.setPageNumber(pagePosts.getNumber());
        response.setPageSize(pagePosts.getSize());
        response.setLastPage(pagePosts.isLast());

        response.setPageSize(pageSize);

        return response;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));

        
        return this.modelMapper.map(post, PostDto.class);
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
        List<Post> posts =  this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos =  posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
