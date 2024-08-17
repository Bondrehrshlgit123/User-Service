package com.example.userservice.controllers;

import com.example.userservice.dtos.AddRolestoUserDto;
import com.example.userservice.dtos.UserDto;
import com.example.userservice.dtos.UserResponseDto;
import com.example.userservice.models.User;
import com.example.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    public UserResponseDto getUserDetails(@PathVariable("id") Long id){
        User user=userService.getUserDetails(id);
        return UserResponseDto.from(user);
    }

//    @PostMapping("/{id}/roles")
//    public UserDto addRolesToUser(@PathVariable("id") Long id, @RequestBody AddRolestoUserDto addRolestoUserDto){
//        Set<Long> roleids=addRolestoUserDto.getRoleIds();
//        //User user=userService.getUserDetails(id);
//        UserDto userDto=userService.addRolesToUser(id,roleids);
//        return userDto;
//    }

}
