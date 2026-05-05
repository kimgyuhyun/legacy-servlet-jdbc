package com.legacy.practice.step3.dto;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class UserDto {

    private Long id;
    private String name;
    private Integer age;
    private LocalDate birthDate;
    private String address;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public UserDto() {}

    public UserDto(Long id, String name, Integer age, LocalDate birthDate, String address,
                   LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.address = address;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}