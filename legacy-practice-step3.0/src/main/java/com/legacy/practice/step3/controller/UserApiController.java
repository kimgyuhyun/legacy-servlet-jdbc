package com.legacy.practice.step3.controller;

import com.legacy.practice.step3.dto.UserResponseDto;
import com.legacy.practice.step3.entity.User;
import com.legacy.practice.step3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user/api")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @GetMapping("/list")
    public List<UserResponseDto> getUserList() {
        List<User> list = userService.getAllUserList();
        List<UserResponseDto> dtoList = new ArrayList<>();

        for (User user : list) {
            dtoList.add(UserResponseDto.formAllArgs(user));
        }
        return dtoList;
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return UserResponseDto.formAllArgs(userService.getUserById(id));
    }


}
