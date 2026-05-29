package com.legacy.practice.step3.dto;

import com.legacy.practice.step3.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserUpdateRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String address;

    public User toEntity() {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setBirthDate(birthDate);
        user.setAddress(address);

        return user;
    }
}
