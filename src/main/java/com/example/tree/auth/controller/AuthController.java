package com.example.tree.auth.controller;

import com.example.tree.auth.dto.LoginDto;
import com.example.tree.auth.dto.LoginResultDto;
import com.example.tree.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

//    @PostMapping("/login")
//    public LoginResultDto login(@RequestBody LoginDto dto) {
//        return authService.login(dto);
//    }

    @PostMapping("/login")
    public LoginResultDto loginWithAuthenticationManager(@RequestBody LoginDto dto) {
        return authService.loginWithAuthenticationManager(dto);
    }
}
