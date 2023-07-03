package com.sparta.blog.dto;

import lombok.Getter;

@Getter
public class BlogRequestDto {

    private String title;
    private String username;
    private String pw;
    private String contents;
}