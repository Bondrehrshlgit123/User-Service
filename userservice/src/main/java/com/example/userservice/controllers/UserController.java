package com.example.userservice.controllers;

import com.example.userservice.dtos.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users/")
public class UserController {
    @GetMapping("/{id}")
    public UserDto getUserDetails(@PathVariable("id") Long id){
        return null;
    }

}
