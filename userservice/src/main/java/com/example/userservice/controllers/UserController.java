package com.example.userservice.controllers;

import com.example.userservice.dtos.AddRolestoUserDto;
import com.example.userservice.dtos.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users/")
public class UserController {
    @GetMapping("/{id}")
    public UserDto getUserDetails(@PathVariable("id") Long id){
        return null;
    }

    @PostMapping("/{id}/roles")
    public void addRolesToUser(@PathVariable("id") Long id, @RequestBody AddRolestoUserDto addRolestoUserDto){
        List<Long> roleids=addRolestoUserDto.getRoleIds();

    }

}
