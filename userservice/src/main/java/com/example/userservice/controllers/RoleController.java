package com.example.userservice.controllers;

import com.example.userservice.dtos.CreateRoleRequestDto;
import com.example.userservice.dtos.GetRoleResponseDto;
import com.example.userservice.models.Role;
import com.example.userservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    RoleService roleService;
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequestDto createRoleRequestDto){
        Role role=roleService.createRole(createRoleRequestDto.getRole());
        return new ResponseEntity<>(role, HttpStatusCode.valueOf(200));
    }
    @GetMapping
    public List<GetRoleResponseDto> getAllRoles(){
        return roleService.getAllRoles();
    }
}
