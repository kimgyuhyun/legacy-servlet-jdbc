package com.legacy.practice.step2.dto;

import java.sql.Timestamp;

public class UserDetailDto {
    private Long id;
    private Long userId;
    private String phone;
    private String job;
    private Timestamp createAt;
    private Timestamp updateAt;

    public UserDetailDto() {}


    public UserDetailDto(Long id, Long userId, String phone, String job,
                         Timestamp createAt, Timestamp updateAt) {
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.job = job;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }
}
