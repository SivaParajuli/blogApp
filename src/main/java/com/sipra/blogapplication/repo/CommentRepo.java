package com.sipra.blogapplication.repo;

import com.sipra.blogapplication.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {
}
