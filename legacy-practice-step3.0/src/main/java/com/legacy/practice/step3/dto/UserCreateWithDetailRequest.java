package com.legacy.practice.step3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;

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

    public UserCreateWithDetailRequest() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

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
