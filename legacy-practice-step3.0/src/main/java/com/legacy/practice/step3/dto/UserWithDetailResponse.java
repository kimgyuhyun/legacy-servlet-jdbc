package com.legacy.practice.step3.dto;

import com.legacy.practice.step3.entity.User;
import com.legacy.practice.step3.entity.UserDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class UserWithDetailResponse {
    private Long id;
    private String name;
    private Integer age;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String job;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static UserWithDetailResponse from(User user) {
        UserWithDetailResponse res = new UserWithDetailResponse();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setAge(user.getAge());
        res.setBirthDate(user.getBirthDate());
        res.setAddress(user.getAddress());
        res.setCreateAt(user.getCreateAt());
        res.setUpdateAt(user.getUpdateAt());

        UserDetail detail = user.getDetail();
        if (detail != null) {
            res.setPhone(detail.getPhone());
            res.setJob(detail.getJob());
        }

        return res;
    }


}
