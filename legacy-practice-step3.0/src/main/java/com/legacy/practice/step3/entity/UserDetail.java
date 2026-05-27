package com.legacy.practice.step3.entity;

import jakarta.persistence.*;
import jakarta.servlet.annotation.ServletSecurity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_detail")
@Getter
@Setter
@ServletSecurity
@NoArgsConstructor
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "phone", nullable = false, length = 30)
    private String phone;

    @Column(name = "job", nullable = false, length = 100)
    private String job;

    @Column(name = "create_at", insertable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at", insertable = false, updatable = false)
    private LocalDateTime updateAt;

    public UserDetail(String phone, String job) {
        this.phone = phone;
        this.job = job;
    }

    public UserDetail(String phone, String job, User user) {
        this.phone = phone;
        this.job = job;
        this.user = user;
    }

    public void updateInfo(String phone, String job) {
        this.phone = phone;
        this.job = job;
    }
}
