package com.sipra.blogapplication.services.impl;

import com.sipra.blogapplication.exceptions.ResourceNotFoundException;
import com.sipra.blogapplication.models.Comment;
import com.sipra.blogapplication.models.Post;
import com.sipra.blogapplication.payLoads.CommentDto;
import com.sipra.blogapplication.repo.CommentRepo;
import com.sipra.blogapplication.repo.PostRepo;
import com.sipra.blogapplication.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
        Comment comment = this.dtoToComment(commentDto);
        comment.setPost(post);
        return this.commentToDto(commentRepo.save(comment));
    }
    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Comment Id",commentId));
        commentRepo.delete(comment);
    }
    private CommentDto commentToDto(Comment comment){ return modelMapper.map(comment,CommentDto.class);}
    private Comment dtoToComment(CommentDto commentDto){ return modelMapper.map(commentDto,Comment.class);}
}
