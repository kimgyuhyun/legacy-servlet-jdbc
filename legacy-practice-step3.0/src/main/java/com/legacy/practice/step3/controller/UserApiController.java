package com.legacy.practice.step3.controller;

import com.legacy.practice.step3.dto.UserResponseDto;
import com.legacy.practice.step3.entity.User;
import com.legacy.practice.step3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/api")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return UserResponseDto.formAllArgs(userService.getUserById(id));
    }
}
