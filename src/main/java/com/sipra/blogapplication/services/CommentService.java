package com.sipra.blogapplication.services;

import com.sipra.blogapplication.payLoads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Long postId);
    void deleteComment(Long commentDto);
}
