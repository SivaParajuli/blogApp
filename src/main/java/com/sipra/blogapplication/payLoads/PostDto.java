package com.sipra.blogapplication.payLoads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Set;

public class PostDto {
    private Long postId;
    @NotEmpty
    @Size(min=3, message = "Title must be at least 3 character.")
    private String title;
    @NotEmpty(message = "Content mustn't be empty")
    private String content;
    private String pImage;
    private Date date;
    private CategoryDto category;
    private UserDto user;
    private Long categoryId;
    private Set<CommentDto> commentSet;

    public PostDto(){
        super();
    }

    public PostDto(Long postId, String title, String content, String pImage, Date date, CategoryDto category, UserDto user, Long categoryId) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.pImage = pImage;
        this.date = date;
        this.category = category;
        this.user = user;
        this.categoryId = categoryId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
