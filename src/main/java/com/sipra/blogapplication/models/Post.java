package com.sipra.blogapplication.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="tbl_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    @Column(length = 10000)
    private String content;
    private String pImage;
    private Date addedDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private Set<Comment> commentSet;

    public Post(){
        super();
    }

    public Post(Long postId, String title, String content, String pImage, Date addedDate, User user, Category category, Set<Comment> commentSet) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.pImage = pImage;
        this.addedDate = addedDate;
        this.user = user;
        this.category = category;
        this.commentSet = commentSet;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
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

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }
}
