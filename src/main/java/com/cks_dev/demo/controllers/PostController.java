package com.cks_dev.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cks_dev.demo.config.AppConstants;
import com.cks_dev.demo.payloads.ApiResponse;
import com.cks_dev.demo.payloads.PostDto;
import com.cks_dev.demo.payloads.PostResponse;
import com.cks_dev.demo.services.FileService;
import com.cks_dev.demo.services.PostService;

import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/api/v1/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;
    
    @Value("${project.image}")
    private String path;


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

    @GetMapping("post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    @GetMapping("posts")
    public ResponseEntity<PostResponse> getAllPost(
        @RequestParam(value="pageNumber" , defaultValue=AppConstants.PAGE_NUMBER, required=false) Integer pageNumber,
         @RequestParam(value="pageSize" , defaultValue=AppConstants.PAGE_SIZE, required=false) Integer pageSize,
         @RequestParam(value="sortBy" ,  defaultValue=AppConstants.SORT_BY, required=false) String sortBy,
         @RequestParam(value="sortDir" , defaultValue=AppConstants.SORT_DIR, required=false) String sortDir
        ){ 
        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize);
        return new ResponseEntity<PostResponse>(postResponse , HttpStatus.OK);
    }
    
    @DeleteMapping("post/{postId}")
    public ApiResponse deletePostById(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post Is Successfully deleted", true);
    }

    @PutMapping("post/{postId}")
    public ResponseEntity<PostDto> updatedPost(@RequestBody PostDto postDto, @PathVariable Integer postId){
       PostDto postDtos = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(postDtos , HttpStatus.OK);
    }

    @GetMapping("posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
        List<PostDto> postDtos = this.postService.searchPost(keywords);
        return new ResponseEntity<List<PostDto>>( postDtos, HttpStatus.OK);
    }

    @PostMapping("post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(
        @RequestParam("image") MultipartFile image,
        @PathVariable Integer postId
    )throws IOException {
        PostDto postDto =  this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatedPostDto = this.postService.updatePost(postDto, postId);

        return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
    }


    @GetMapping(value="post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
    InputStream resource = this.fileService.getResource(path, imageName);
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
     IOUtils.copy(resource, response.getOutputStream());
    response.flushBuffer();
}


}
