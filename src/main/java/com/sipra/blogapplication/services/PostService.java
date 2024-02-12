package com.sipra.blogapplication.services;
import com.sipra.blogapplication.payLoads.PostDto;
import com.sipra.blogapplication.payLoads.PostPageDto;
import com.sipra.blogapplication.payLoads.ResponseDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Long userId);
    PostDto updatePost(PostDto postDto,Long postId);
    void deletePost(Long postId);
    PostDto getPostById(Long postId);
    PostPageDto getAllPosts(Integer pageNumber, Integer pageSize, String sortBY, String sortDir);
    List<PostDto> getAllPostsByCategory(Long categoryId);
    List<PostDto> getAllPostByUser(Long userId);
    List<PostDto> searchPost(String keyword);

}
