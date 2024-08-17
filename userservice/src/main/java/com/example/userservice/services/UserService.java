package com.example.userservice.services;

import com.example.userservice.dtos.UserDto;
import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import com.example.userservice.repositories.RoleRepository;
import com.example.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;

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
//    public UserDto addRolesToUser(Long id, Set<Long> roleIds){
//       // List<Role> roles=roleRepository.findAllById(roleIds);
//        Optional<User> userDtoOptional=userRepository.findById(id);
//        User user= userDtoOptional.get();
//        Set<Role> roles=new HashSet<>();
//        if(roleIds!=null){
//            for(Long id1:roleIds){
//                if(id1!=null){
//                    Optional<Role> roleOptional= Optional.ofNullable(roleRepository.findById(id1)
//                            .orElseThrow(() -> new IllegalArgumentException("Role not found")));
//                    Role role= roleOptional.get();
//                    roles.add(role);
//                    user.addRole(role);
//                }
//
//
//            }
//        }
//        else {
//            throw new IllegalArgumentException("Roleids are null");
//        }
//
////        Set<Role> roleSet=new HashSet<>(roles);
////        user.setRoles(roleSet);
//        User saveduser=userRepository.save(user);
//        return UserDto.from(saveduser);
//    }

}
