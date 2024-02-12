package com.sipra.blogapplication.controller;

import com.sipra.blogapplication.payLoads.CommentDto;
import com.sipra.blogapplication.payLoads.ResponseDto;
import com.sipra.blogapplication.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ResponseDto>createComment(CommentDto commentDto, @PathVariable("postId")Long postId){
        CommentDto comment = commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(new ResponseDto(true,comment,"Comment Updated"), HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable("commentId")Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ResponseDto(true,"Comment Deleted"),HttpStatus.OK);
    }
}
