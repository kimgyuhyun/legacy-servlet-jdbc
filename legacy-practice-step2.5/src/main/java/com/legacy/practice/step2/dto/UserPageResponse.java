package com.legacy.practice.step2.dto;

import java.util.ArrayList;
import java.util.List;

public class UserPageResponse {

    private List<UserDto> content = new ArrayList<>();
    private long totalElements;
    private int page;
    private int size;
    private int totalPages;


    public UserPageResponse() {
    }


    public List<UserDto> getContent() {
        return content;
    }

    public void setContent(List<UserDto> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
