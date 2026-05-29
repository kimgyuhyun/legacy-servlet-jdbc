package com.legacy.practice.step3.dto;

import com.legacy.practice.step3.entity.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserPatchRequest {

    private String name;

    private Integer age;

    private LocalDate birthDate;

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
