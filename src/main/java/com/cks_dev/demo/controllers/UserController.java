package com.cks_dev.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.cks_dev.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cks_dev.demo.payloads.UserDto;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Post-create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUSerDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUSerDto, HttpStatus.CREATED);
    }
    // PUT -update user
    // Delete -delete user
}
