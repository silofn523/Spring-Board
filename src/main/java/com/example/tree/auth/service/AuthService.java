package com.example.tree.auth.service;

import com.example.tree.auth.dto.LoginDto;
import com.example.tree.auth.dto.LoginResultDto;
import com.example.tree.user.Entity.User;
import com.example.tree.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.example.tree.util.JwtUtil;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

//    public LoginResultDto login(LoginDto dto) {
//        User user = userRepository.findById(dto.getId()).orElse(null);
//        if (user == null) {
//            throw new IllegalArgumentException("이 사용자는 없습니다");
//        }
//
//        if (!user.getPassword().equals(dto.getPassword())) {
//            throw new IllegalArgumentException("비번이 틀렸습니다");
//        }
//
//        return new LoginResultDto(jwtUtil.generateToken(user));
//    }

    public LoginResultDto loginWithAuthenticationManager(LoginDto dto) {
        var request = new UsernamePasswordAuthenticationToken(
                dto.getId(),dto.getPassword()
        );

        var result = authenticationManager.authenticate(request);
        return new LoginResultDto(jwtUtil.generateToken((User) result.getPrincipal()));
    }
}
