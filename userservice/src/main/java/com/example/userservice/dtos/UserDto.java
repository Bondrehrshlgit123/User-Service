package com.example.userservice.dtos;

import com.example.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    public static UserDto from(User user){
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
