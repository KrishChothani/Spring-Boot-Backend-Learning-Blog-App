package com.cks_dev.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cks_dev.demo.payloads.PostDto;
import com.cks_dev.demo.services.PostService;


@RestController
@RequestMapping("/api/v1/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(
        @RequestBody PostDto postDto,
        @PathVariable Integer userId,
        @PathVariable Integer categoryId
    ){
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> postDtos = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getpostByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtos = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }

}
