package com.legacy.practice.step3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name", nullable = false, length = 100)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "create_at", insertable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at", insertable = false, updatable = false)
    private LocalDateTime updateAt;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private UserDetail detail;

    public User(Long id, String name, Integer age, LocalDate birthDate, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.address = address;
    }

    public void updateProfile(String name, Integer age, LocalDate birthDate, String address) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.address = address;
    }

}
