package com.example.userservice.services;

import com.example.userservice.dtos.UserDto;
import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import com.example.userservice.repositories.RoleRepository;
import com.example.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    public User getUserDetails(Long id){
        Optional<User> userDtoOptional=userRepository.findById(id);
        User user= userDtoOptional.get();
//        UserDto userDto= UserDto.from(user);
        return user;
    }
    public UserDto addRolesToUser(User user, List<Long> roleIds){
        List<Role> roles=roleRepository.findAllById(roleIds);
        Set<Role> roleSet=new HashSet<>(roles);
        user.setRoles(roleSet);
        User saveduser=userRepository.save(user);
        return UserDto.from(saveduser);
    }

}
