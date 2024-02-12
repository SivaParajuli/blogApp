package com.sipra.blogapplication.services;

import com.sipra.blogapplication.models.Category;
import com.sipra.blogapplication.payLoads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);
    void deleteCategory(Long categoryId);
    CategoryDto getCategoryById(Long categoryId);
    List<CategoryDto> getAllCategories();

}
