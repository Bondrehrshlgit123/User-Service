package com.example.userservice.controllers;

import com.example.userservice.dtos.*;
import com.example.userservice.models.SessionStatus;
import com.example.userservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());

    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto){
        authService.logout(requestDto.getUser_id(), requestDto.getToken());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody SignupRequestDto requestDto){
        UserResponseDto userDto=authService.signup(requestDto.getEmail(),requestDto.getPassword(),requestDto.getRole().getId());
        return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(200));
    }
    @PostMapping("/validate")
    public ResponseEntity<SessionValidateResponseDto> validate(@RequestBody ValidateTokenRequestDto validateTokenRequestDto){
        SessionValidateResponseDto sessionValidateResponseDto=authService.validate(validateTokenRequestDto.getUser_id(),
                validateTokenRequestDto.getToken());
        return new ResponseEntity<>(sessionValidateResponseDto,HttpStatusCode.valueOf(200));
    }
}
