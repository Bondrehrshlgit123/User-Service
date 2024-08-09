package com.example.userservice.controllers;

import com.example.userservice.dtos.AddRolestoUserDto;
import com.example.userservice.dtos.UserDto;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users/")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    public UserDto getUserDetails(@PathVariable("id") Long id){
        User user=userService.getUserDetails(id);
        return UserDto.from(user);
    }

    @PostMapping("/{id}/roles")
    public void addRolesToUser(@PathVariable("id") Long id, @RequestBody AddRolestoUserDto addRolestoUserDto){
        List<Long> roleids=addRolestoUserDto.getRoleIds();
        User user=userService.getUserDetails(id);
        UserDto userDto=userService.addRolesToUser(user,roleids);

    }

}
