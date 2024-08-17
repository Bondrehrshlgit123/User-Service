package com.example.userservice.services;

import com.example.userservice.dtos.GetRoleResponseDto;
import com.example.userservice.models.Role;
import com.example.userservice.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    public Role createRole(String rolename){
        Role role=new Role();
        role.setRole(rolename);
        return roleRepository.save(role);
    }
    public List<GetRoleResponseDto> getAllRoles(){
        List<GetRoleResponseDto> getRoleResponseDtos=new ArrayList<>();
        List<Role> roles=roleRepository.findAll();
        for(Role role:roles){
            GetRoleResponseDto getRoleResponseDto=GetRoleResponseDto.from(role);
            getRoleResponseDtos.add(getRoleResponseDto);
        }
        return getRoleResponseDtos;
    }
}
