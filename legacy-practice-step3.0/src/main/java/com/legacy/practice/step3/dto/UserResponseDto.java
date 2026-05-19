package com.legacy.practice.step3.dto;

import com.legacy.practice.step3.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private Integer age;
    private LocalDate birthDate;
    private String address;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

//    public static UserResponseDto from(User user) {
//        UserResponseDto dto = new UserResponseDto();
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

    public static UserResponseDto formAllArgs(User user) {
        return new UserResponseDto(
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
