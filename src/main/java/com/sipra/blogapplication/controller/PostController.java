package com.sipra.blogapplication.controller;

import com.sipra.blogapplication.config.AppConstants;
import com.sipra.blogapplication.payLoads.PostDto;
import com.sipra.blogapplication.payLoads.PostPageDto;
import com.sipra.blogapplication.payLoads.ResponseDto;
import com.sipra.blogapplication.services.FileService;
import com.sipra.blogapplication.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @PostMapping("/users/{userId}/posts")
    public ResponseEntity<ResponseDto>createPost(@Valid @RequestBody PostDto postDto, @PathVariable("userId")Long userId){
        PostDto createdPost = postService.createPost(postDto,userId);
        return new ResponseEntity<>(new ResponseDto(true,createdPost,"Post Created."), HttpStatus.CREATED);
    }
    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<ResponseDto>getAllPostByUser(@PathVariable("userId")Long userId){
        List<PostDto> posts=postService.getAllPostByUser(userId);
        return new ResponseEntity<>(new ResponseDto(true,posts,"Posts Fetched according to user"),HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<ResponseDto>getAllPostByCategory(@PathVariable("categoryId")Long categoryId){
        List<PostDto> posts=postService.getAllPostsByCategory(categoryId);
        return  new ResponseEntity<>(new ResponseDto(true,posts,"Posts fetched according to category. "),HttpStatus.OK);
    }
    @GetMapping("/posts")
    public ResponseEntity<ResponseDto>getAllPosts(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER, required = false)Integer pageNumber,
                                                  @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE, required = false)Integer pageSize,
                                                  @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY, required=false)String sortBy,
                                                  @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR, required = false)String sortDir){
        PostPageDto postPages = postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(new ResponseDto(true,postPages,"All post Fetched"),HttpStatus.OK);
    }
    @GetMapping("posts/{postId}")
    public ResponseEntity<ResponseDto> getPostById(@PathVariable("postId")Long postId){
        PostDto posts = postService.getPostById(postId);
        return new ResponseEntity<>(new ResponseDto(true,posts,"Post Fetched"),HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ResponseDto> deletePostById(@PathVariable("postId")Long postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(new ResponseDto(true,"Post Deleted"),HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}")
    public ResponseEntity<ResponseDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable("postId")Long postId){
        PostDto updatedPost = postService.updatePost(postDto,postId);
        return new ResponseEntity<>(new ResponseDto(true, updatedPost,"Post Updated"),HttpStatus.OK);
    }
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<ResponseDto> searchPostByTitle(@PathVariable("keywords")String keywords){
        List<PostDto> searchedPosts = postService.searchPost(keywords);
        return new ResponseEntity<>(new ResponseDto(true,searchedPosts,"Post Searched"),HttpStatus.OK);
    }
    @PostMapping("/posts/{postId}/upload_image")
    public ResponseEntity<ResponseDto> uploadImage(@RequestParam("image") MultipartFile file,@PathVariable("postId")Long postId) throws IOException {
        String fileName = fileService.uploadImage(file);
        PostDto postDto = postService.getPostById(postId);
        postDto.setpImage(fileName);
        PostDto updatedPost = postService.updatePost(postDto,postId);
        return new ResponseEntity<>(new ResponseDto(true,updatedPost,"Post updated with image."),HttpStatus.OK);
    }
    @GetMapping(value= "/posts/image/{imageName}", produces= MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName")String image, HttpServletResponse response) throws IOException {
        InputStream inputStream = fileService.getResource(image);
        StreamUtils.copy(inputStream,response.getOutputStream());
    }
}
