package com.sipra.blogapplication.models;

import jakarta.persistence.*;

@Entity
@Table(name="tbl_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String comment;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;

    public Comment() {
       super();
    }

    public Comment(Long commentId, String comment, User user, Post post) {
        this.commentId = commentId;
        this.comment = comment;
        this.user = user;
        this.post = post;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
