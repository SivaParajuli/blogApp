package com.sipra.blogapplication.payLoads;

import java.util.List;

public class PostPageDto {
    private List<PostDto> posts;
    private int pageNumber;
    private int totalPages;
    private int totalElements;
    private int pageSize;
    private boolean lastPage;

    public PostPageDto(){

    }
    public PostPageDto(List<PostDto> posts, int pageNumber,int pageSize, int totalPages, int totalElements, boolean lastPage) {
        this.posts = posts;
        this.pageNumber = pageNumber;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.lastPage = lastPage;
    }
    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }
}
