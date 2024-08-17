package com.example.userservice.dtos;

import com.example.userservice.models.Role;
import com.example.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String email;
    private String role;
    public static UserResponseDto from(User user){
        UserResponseDto userDto=new UserResponseDto();
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().getRole());
        return userDto;
    }
}
