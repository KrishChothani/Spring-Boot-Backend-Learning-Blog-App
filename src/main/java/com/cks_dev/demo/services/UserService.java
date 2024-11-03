package com.cks_dev.demo.services;

import java.util.List;

import com.cks_dev.demo.payloads.UserDto;

public interface UserService {

    UserDto createUser(UserDto user) ;
    UserDto updateUser(UserDto user , Integer userId) ;
    UserDto getUserById(Integer userId) ;
    List<UserDto> getAllUSers() ;
    void deleteUser(Integer userId) ;
    
}
