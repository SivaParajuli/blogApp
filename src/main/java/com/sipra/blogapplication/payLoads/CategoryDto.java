package com.sipra.blogapplication.payLoads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDto {
    private Long categoryId;
    @NotEmpty
    @Size(min=3, message="Title must contain 3 character.")
    private String categoryTitle;
    @NotEmpty(message = "Description mustn't be empty")
    private String categoryDescription;

    public CategoryDto(Long categoryId, String categoryTitle, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryDescription = categoryDescription;
    }

    public CategoryDto(){
        super();
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
