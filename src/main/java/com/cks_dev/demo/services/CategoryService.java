package com.cks_dev.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cks_dev.demo.payloads.CategoryDto;

@Service
public interface CategoryService {

    // Create 
    CategoryDto createCategoryDto(CategoryDto categoryDto);

    //UPDATE
    CategoryDto updateCategoryDto(CategoryDto categoryDto , Integer categoryId);

    //DELETE
    void deleteCategory(Integer categoryId); 

    //GET
    CategoryDto getCategoryById(Integer categoryId);
    
    // GET ALL
    List<CategoryDto> getAllCategories();

}
