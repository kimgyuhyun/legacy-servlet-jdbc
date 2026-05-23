package com.legacy.practice.step3.dto;

import com.legacy.practice.step3.entity.User;
import com.legacy.practice.step3.entity.UserDetail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class UserWithDetailResponse {

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @NotBlank
    private String job;

    public static UserWithDetailResponse toUserWithDetail(User user) {
        UserWithDetailResponse res = new UserWithDetailResponse();
        res.setName(user.getName());
        res.setAge(user.getAge());
        res.setBirthDate(user.getBirthDate());
        res.setAddress(user.getAddress());
        res.setPhone(user.getDetail().getPhone());
        res.setJob(user.getDetail().getJob());

        return res;
    }


}
