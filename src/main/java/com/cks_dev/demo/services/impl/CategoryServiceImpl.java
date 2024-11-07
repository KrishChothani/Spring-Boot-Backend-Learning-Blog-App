package com.cks_dev.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cks_dev.demo.Excesption.ResourceNotFoundException;
import com.cks_dev.demo.models.Category;
import com.cks_dev.demo.payloads.CategoryDto;
import com.cks_dev.demo.repository.CategoryRepo;
import com.cks_dev.demo.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategoryDto(CategoryDto categoryDto) {
        // Implement the logic to create a category
        // For now, just return the input as a placeholder

        Category cat =  this.modelMapper.map(categoryDto, Category.class);
        Category addCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(addCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategoryDto(CategoryDto categoryDto, Integer categoryId) {
        // Implement the logic to update a category based on categoryId
        // For now, just return the input as a placeholder
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CAtegory Id", categoryId ));
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        cat.setCategoryTitle(categoryDto.getCategoryTitle());

        Category updatedCat =  this.categoryRepo.save(cat);

        return this.modelMapper.map(updatedCat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        // Implement the logic to delete a category based on categoryId
        // Placeholder code
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        // Implement the logic to get a category by ID
        // Return a placeholder CategoryDto object
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));

        return this.modelMapper.map(cat, CategoryDto.class); // replace with actual fetching logic
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        // Implement the logic to get all categories
        // Return a placeholder list of categories
        List<Category> cat =  this.categoryRepo.findAll();
        List<CategoryDto> catDto =  cat.stream().map((c) -> modelMapper.map(c, CategoryDto.class)).collect(Collectors.toList());
        return catDto; // replace with actual fetching logic
    }
}
