package com.example.userservice.dtos;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ValidateTokenRequestDto {
    private Long user_id;
    private String token;
}
