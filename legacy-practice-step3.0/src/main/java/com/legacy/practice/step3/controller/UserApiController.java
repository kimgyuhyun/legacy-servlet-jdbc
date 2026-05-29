package com.legacy.practice.step3.controller;

import com.legacy.practice.step3.dto.*;
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

    @PostMapping("/join/create/user/Detail")
    public ResponseEntity<UserWithDetailResponse> createUserWithDetail(
            @Valid @RequestBody UserCreateWithDetailRequest req) {
        User user = userService.insertUserWithDetail(req);
        return ResponseEntity.status(201).body(UserWithDetailResponse.toUserWithDetail(user));
    }

    @PutMapping("/update/put/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @Valid @RequestBody UserUpdateRequest req) {
        User user = userService.updateUser(id, req);
        return ResponseEntity.status(200).body(UserResponse.fromAllArgs(user));
    }

    @PatchMapping("/update/patch/{id}")
    public ResponseEntity<UserResponse> updatePatchUser(@PathVariable Long id,
                                                        @RequestBody UserPatchRequest req) {
        User user = userService.patchUpdateuser(id, req);
        return ResponseEntity.status(200).body(UserResponse.fromAllArgs(user));
    }
}
