package com.cks_dev.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cks_dev.demo.Excesption.ResourceNotFoundException;
import com.cks_dev.demo.Excesption.UserAlreadyExistsException;
import com.cks_dev.demo.models.User;
import com.cks_dev.demo.payloads.UserDto;
import com.cks_dev.demo.repository.UserRepo;
import com.cks_dev.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Logic to create user 

       if (userRepo.existsByEmail(userDto.getEmail())) {
        throw new UserAlreadyExistsException("User", "email", userDto.getEmail());
    }

    // Check if user already exists by username
    if (userRepo.existsByName(userDto.getName())) {
        throw new UserAlreadyExistsException("User", "name", userDto.getName());
    }
        User user = this .dtoTOUser(userDto);
        User savedUser =  this.userRepo.save(user);
        
        return this.UserToDto(savedUser);  // Placeholder for actual implementation
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User" , " id " , userId) );
        
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        
        User updateUser = this.userRepo.save(user);
        UserDto userDto1 = this.UserToDto(updateUser);

        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        // Logic to get user by userId
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId)); 
        return this.UserToDto(user);     // Placeholder for actual implementation
    }

    @Override
    public List<UserDto> getAllUSers() {
        // Logic to get all users
       List<User> users =  this.userRepo.findAll();
       List<UserDto> userDtos = users.stream().map(user -> this.UserToDto(user)).collect(Collectors.toList());
        return userDtos;  // Placeholder for actual implementation
    }

    @Override
    public void deleteUser(Integer userId) {
        // Logic to delete user by userId
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepo.delete(user);
    }


    public User dtoTOUser(UserDto userDto) {

        // <------------------------- Method 1 ------------------------>
        // User user = new User();
        // user.setId(userDto.getId());
        // user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        // user.setPassword(userDto.getPassword());
        // user.setAbout(userDto.getAbout());
        // return user;

        // <------------------------ Method 2 ------------------------->
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto UserToDto(User user) {
        // UserDto userDto = new UserDto();
        // userDto.setId(user.getId());
        // userDto.setName(user.getName());
        // userDto.setEmail(user.getEmail());
        // userDto.setPassword(user.getPassword());
        // userDto.setAbout(user.getAbout());
        // return userDto;

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
