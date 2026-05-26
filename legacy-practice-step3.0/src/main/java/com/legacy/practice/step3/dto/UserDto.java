package com.legacy.practice.step3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class UserDto {

    private Long id;
    private String name;
    private Integer age;
    private LocalDate birthDate;
    private String address;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


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


}