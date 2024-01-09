package com.example.tree.user.service;

import com.example.tree.user.Dto.UserDto;
import com.example.tree.user.Entity.User;
import com.example.tree.user.repository.UserRepository;
import com.example.tree.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User createUser(UserDto dto) {
        LocalDateTime now = LocalDateTime.now();

        User user = new User(
                dto.getId(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getName(),
                dto.getEmail(),
                now,
                now
        );

        return userRepository.save(user);
    }

//    public User getOneUser(int id) {
//        return userRepository.findById(id);
//    }
    public User getMyProfileByToken(String token) {
        String userId = jwtUtil.getSubject(token);
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("존재하지 않는 사용자 입니다");
        }
        return userRepository.findById(userId).get();
    }
}
