package com.legacy.practice.step3.controller;

import com.legacy.practice.step3.dto.UserRequest;
import com.legacy.practice.step3.dto.UserResponse;
import com.legacy.practice.step3.dto.UserWithDetailResponse;
import com.legacy.practice.step3.entity.User;
import com.legacy.practice.step3.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user/api")
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;

    @GetMapping("/list")
    public List<UserResponse> getUserList() {
        List<User> list = userService.loadAllUserList();
        List<UserResponse> dtoList = new ArrayList<>();

        for (User user : list) {
            dtoList.add(UserResponse.fromAllArgs(user));
        }
        return dtoList;
    }

    @GetMapping("/detail/list")
    public List<UserWithDetailResponse> getUserWithDetailList() {
        List<User> list = userService.loadAllUserWithDetailList();
        List<UserWithDetailResponse> dtoList = new ArrayList<>();

        for (User user : list) {
            dtoList.add(UserWithDetailResponse.toUserWithDetail(user));
        }

        return dtoList;
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return UserResponse.fromAllArgs(userService.loadUserById(id));
    }

    @GetMapping("/detail/{id}")
    public UserWithDetailResponse getUserWithDetailById(@PathVariable Long id) {
        return UserWithDetailResponse.toUserWithDetail(
                userService.loadUserWithDetailById(id));
    }


    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest req) {
        User user = userService.insertUser(req.toEntity());
        return ResponseEntity.status(201).body(UserResponse.fromAllArgs(user));
    }
}
