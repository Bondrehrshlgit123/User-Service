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
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());

    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto){
        authService.logout(requestDto.getUser_id(), requestDto.getToken());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto requestDto){
        UserDto userDto=authService.signup(requestDto.getEmail(),requestDto.getPassword());
        return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(200));
    }
    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validate(@RequestBody ValidateTokenRequestDto validateTokenRequestDto){
        SessionStatus sessionStatus=authService.validate(validateTokenRequestDto.getUser_id(),
                validateTokenRequestDto.getToken());
        return new ResponseEntity<>(sessionStatus,HttpStatusCode.valueOf(200));
    }
}
