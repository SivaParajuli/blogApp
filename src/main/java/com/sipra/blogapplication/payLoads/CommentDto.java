package com.sipra.blogapplication.payLoads;

public class CommentDto {
    private Long commentId;
    private String comment;

    public CommentDto() {
    }

    public CommentDto(Long commentId, String comment) {
        this.commentId = commentId;
        this.comment = comment;
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

}
