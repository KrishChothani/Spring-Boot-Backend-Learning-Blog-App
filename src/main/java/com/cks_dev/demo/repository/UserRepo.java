package com.cks_dev.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cks_dev.demo.models.User;

// @Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    boolean existsByName(String name);
}
