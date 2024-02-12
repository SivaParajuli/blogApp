package com.sipra.blogapplication.services.impl;

import com.sipra.blogapplication.config.AppConstants;
import com.sipra.blogapplication.exceptions.ResourceNotFoundException;
import com.sipra.blogapplication.models.Category;
import com.sipra.blogapplication.models.Post;
import com.sipra.blogapplication.models.User;
import com.sipra.blogapplication.payLoads.PostDto;
import com.sipra.blogapplication.payLoads.PostPageDto;
import com.sipra.blogapplication.payLoads.ResponseDto;
import com.sipra.blogapplication.repo.CategoryRepo;
import com.sipra.blogapplication.repo.PostRepo;
import com.sipra.blogapplication.repo.UserRepo;
import com.sipra.blogapplication.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public PostDto createPost(PostDto postDto, Long userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
        Long cId =  postDto.getCategoryId();
        Category category = categoryRepo.findById(cId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",cId));
        Post post =this.dtoToPost(postDto);
        post.setUser(user);
        post.setCategory(category);
        post.setAddedDate(new Date());
        Post savedPost =  postRepo.save(post);
        return this.postToDto(savedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post updatedPost=postRepo.save(post);
        return this.postToDto(updatedPost);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        postRepo.delete(post);
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        return this.postToDto(post);
    }

    @Override
    public PostPageDto getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort =sortDir.equalsIgnoreCase(AppConstants.SORT_DIR) ? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePosts = postRepo.findAll(pageable);
        List<Post> allPosts = pagePosts.getContent();
        List<PostDto> postList =  allPosts.stream().map(this::postToDto).toList();
        PostPageDto postPage = new PostPageDto(postList,pagePosts.getNumber(),pagePosts.getSize(),pagePosts.getTotalPages(),pagePosts.getTotalPages(),pagePosts.isLast());
        return postPage;
    }
    @Override
    public List<PostDto> getAllPostsByCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        List<Post> posts = postRepo.findByCategory(category);
        List<PostDto> postList = posts.stream().map(this::postToDto).toList();
        return postList;
    }

    @Override
    public List<PostDto> getAllPostByUser(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> postList = posts.stream().map(this::postToDto).toList();
        return postList;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> posts= postRepo.findPostByTitle("%"+keyword+"%");
        List<PostDto> searchedPosts = posts.stream().map(this::postToDto).toList();
        return searchedPosts;
    }

    private PostDto postToDto(Post post){
       return modelMapper.map(post,PostDto.class);
    }
    private Post dtoToPost(PostDto postDto){
        return modelMapper.map(postDto,Post.class);
    }
}
