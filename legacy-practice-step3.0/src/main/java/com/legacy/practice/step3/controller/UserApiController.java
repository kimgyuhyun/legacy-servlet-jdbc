package com.legacy.practice.step3.controller;

import com.legacy.practice.step3.dto.*;
import com.legacy.practice.step3.entity.User;
import com.legacy.practice.step3.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
            dtoList.add(UserResponse.from(user));
        }
        return dtoList;
    }

    @GetMapping("/detail/list")
    public List<UserWithDetailResponse> getUserWithDetailList() {
        List<User> list = userService.loadAllUserWithDetailList();
        List<UserWithDetailResponse> dtoList = new ArrayList<>();

        for (User user : list) {
            dtoList.add(UserWithDetailResponse.from(user));
        }

        return dtoList;
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return UserResponse.from(userService.loadUserById(id));
    }

    @GetMapping("/detail/{id}")
    public UserWithDetailResponse getUserWithDetailById(@PathVariable Long id) {
        return UserWithDetailResponse.from(
                userService.loadUserWithDetailById(id));
    }


    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest req) {
        User user = userService.insertUser(req.toEntity());
        return ResponseEntity.status(201).body(UserResponse.from(user));
    }

    @PostMapping("/join/create/user/Detail")
    public ResponseEntity<UserWithDetailResponse> createUserWithDetail(
            @Valid @RequestBody UserCreateWithDetailRequest req) {
        User user = userService.insertUserWithDetail(req);
        return ResponseEntity.status(201).body(UserWithDetailResponse.from(user));
    }

    @PutMapping("/update/put/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @Valid @RequestBody UserUpdateRequest req) {
        User user = userService.updateUser(id, req);
        return ResponseEntity.status(200).body(UserResponse.from(user));
    }

    @PatchMapping("/update/patch/{id}")
    public ResponseEntity<UserResponse> updatePatchUser(@PathVariable Long id,
                                                        @RequestBody UserPatchRequest req) {
        User user = userService.patchUpdateuser(id, req);
        return ResponseEntity.status(200).body(UserResponse.from(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 NO Content
    }

    @GetMapping("/dsl/list")
    public List<UserResponse> dslGetuserList() {
        List<User> userList = userService.DslloadAllUserList();
        List<UserResponse> dtoList = new ArrayList<>();

        for (User user : userList) {
            dtoList.add(UserResponse.from(user));
        }

        return dtoList;
    }


    @GetMapping("/dsl/detail/list")
    public List<UserWithDetailResponse> getUserWithDetailListDSL() {
        List<User> list = userService.loadAllUserWithDetailListDSL();
        List<UserWithDetailResponse> dtoList = new ArrayList<>();

        for (User user : list) {
            dtoList.add(UserWithDetailResponse.from(user));
        }

        return dtoList;
    }

    @GetMapping("/dsl/detail/{id}")
    public UserWithDetailResponse getUserWithDetailByIdDSL(@PathVariable Long id) {
        return UserWithDetailResponse.from(
                userService.loadUserWithDetailByIdDSL(id));
    }

    @GetMapping("/dsl/search")
    public List<UserResponse> getSearchuserList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String address) {

        List<User> list = userService.searchUserList(name, age, address);
        List<UserResponse> dtoList = new ArrayList<>();

        for (User user : list) {
            dtoList.add(UserResponse.from(user));
        }

        return dtoList;
    }

    @GetMapping("/dsl/projection/list")
    public List<UserResponse> getProjectionUserList() {
        return userService.loadAllUserResponseByProjection();
    }

    @GetMapping("/dsl/projection/detail/list")
    public List<UserWithDetailResponse> getProjectionUserWithDetailList() {
        return userService.loadAllUserWithDetailByQueryProjection();
    }


    @GetMapping("/dsl/paged")
    public ResponseEntity<Page<UserResponse>> getPagedUserList(Pageable pageable) {
        Page<UserResponse> result = userService.loadPagedUserResponse(pageable);
        return ResponseEntity.ok(result);
    }
}
