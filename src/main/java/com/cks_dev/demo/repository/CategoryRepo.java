package com.cks_dev.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cks_dev.demo.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
