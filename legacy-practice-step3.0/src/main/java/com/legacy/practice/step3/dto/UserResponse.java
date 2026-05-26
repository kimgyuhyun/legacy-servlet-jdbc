package com.legacy.practice.step3.dto;

import com.legacy.practice.step3.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private Integer age;
    private LocalDate birthDate;
    private String address;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

//    public static UserResponse from(User user) {
//        UserResponse dto = new UserResponse();
//
//        dto.setId(user.getId());
//        dto.setName(user.getName());
//        dto.setAge(user.getAge());
//        dto.setBirthDate(user.getBirthDate());
//        dto.setAddress(user.getAddress());
//        dto.setCreateAt(user.getCreateAt());
//        dto.setUpdateAt(user.getUpdateAt());
//
//        return dto;
//    }

    public static UserResponse fromAllArgs(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getBirthDate(),
                user.getAddress(),
                user.getCreateAt(),
                user.getUpdateAt()

        );
    }
}
