package com.sipra.blogapplication.services.impl;

import com.sipra.blogapplication.exceptions.ResourceNotFoundException;
import com.sipra.blogapplication.models.Category;
import com.sipra.blogapplication.payLoads.CategoryDto;
import com.sipra.blogapplication.repo.CategoryRepo;
import com.sipra.blogapplication.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.dtoToCategory(categoryDto);
        Category savedCategory = categoryRepo.save(category);
        return this.categoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        return this.categoryToDto(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        return this.categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categories = categoryRepo.findAll().stream()
                .map(this::categoryToDto).toList();
        return categories;
    }

    private Category dtoToCategory(CategoryDto categoryDto){
        return modelMapper.map(categoryDto,Category.class);
    }
    private CategoryDto categoryToDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }
}
