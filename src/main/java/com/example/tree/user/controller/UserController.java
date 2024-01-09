package com.example.tree.user.controller;

import com.example.tree.user.Dto.UserDto;
import com.example.tree.user.Entity.User;
import com.example.tree.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping
    public User createUser(@RequestBody UserDto dto) {
        return userService.createUser(dto);
    }

//    @GetMapping("/{id}")
//    public User getOneUser(@PathVariable int id) {
//        return userService.getOneUser(id);
//    }

    @GetMapping("/me")
    public User getMyProFileByDumbWay(
            @AuthenticationPrincipal User user
    ) {
       return user;
    }
}
