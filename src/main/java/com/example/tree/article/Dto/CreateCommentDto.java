package com.example.tree.article.Dto;

import lombok.Getter;

@Getter
public class CreateCommentDto {
    private String content;
    private String author;
    private String password;
}
