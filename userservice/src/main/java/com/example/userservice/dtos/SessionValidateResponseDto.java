package com.example.userservice.dtos;

import com.example.userservice.models.SessionStatus;
import com.example.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionValidateResponseDto {
    private String email;
    private String role;
    private SessionStatus sessionStatus;
//    public static SessionValidateResponseDto from(User user){
//        UserResponseDto userDto=new UserResponseDto();
//        userDto.setEmail(user.getEmail());
//        userDto.setRole(user.getRole().getRole());
//        return userDto;
//    }
}
