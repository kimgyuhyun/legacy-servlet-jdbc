package com.legacy.practice.step2.dto;

import java.sql.Date;

public class UserCreateWithDetailRequest {

    private String name;
    private Integer age;
    private Date birthDate;
    private String address;
    private String phone;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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
