package com.sipra.blogapplication.controller;

import com.sipra.blogapplication.payLoads.CategoryDto;
import com.sipra.blogapplication.payLoads.ResponseDto;
import com.sipra.blogapplication.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<ResponseDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(new ResponseDto(true,category,"Category Created"), HttpStatus.CREATED);
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<ResponseDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId")Long id){
        CategoryDto category = categoryService.updateCategory(categoryDto,id);
        return new ResponseEntity<>(new ResponseDto(true,category,"Category Updated"),HttpStatus.OK);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ResponseDto>deleteCategory(@PathVariable("categoryId")Long categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ResponseDto(true,"Category deleted"),HttpStatus.OK);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<ResponseDto>getCategory(@PathVariable("categoryId")Long categoryId){
           CategoryDto category = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(new ResponseDto(true, category,"Category Fetched"),HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<ResponseDto>getAllCategories(){
        List<CategoryDto> category = categoryService.getAllCategories();
        return new ResponseEntity<>(new ResponseDto(true, category,"Category List Fetched"),HttpStatus.OK);
    }
}
