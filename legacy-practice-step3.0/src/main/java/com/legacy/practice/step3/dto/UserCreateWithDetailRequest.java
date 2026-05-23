package com.legacy.practice.step3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class UserCreateWithDetailRequest {

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


    public UserDto toUserDto() {
        UserDto dto = new UserDto();
        dto.setName(name);
        dto.setAge(age);
        dto.setBirthDate(birthDate);
        dto.setAddress(address);

        return dto;
    }

    public UserDetailDto toUserDetailDto(Long userId) {
        UserDetailDto dto = new UserDetailDto();
        dto.setUserId(userId);
        dto.setPhone(phone);
        dto.setJob(job);

        return dto;
    }
}
