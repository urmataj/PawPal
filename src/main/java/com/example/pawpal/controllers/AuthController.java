package com.example.pawpal.controllers;

import com.example.pawpal.dto.SignUpDto;
import com.example.pawpal.dto.SuccessDto;
import com.example.pawpal.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trusted/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public SuccessDto signUp(@Valid @RequestBody SignUpDto signUpDto){
        userService.saveUser(signUpDto);
        return SuccessDto.builder().success(true).build();
    }

}