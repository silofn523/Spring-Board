package com.example.tree.article.model;

import com.example.tree.user.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "articles")
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String author;
    @JsonIgnore
    private String password;
    @ManyToOne
    private User user;
//    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = { CascadeType.REMOVE })
    private List<Comment> comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Alt + Ins 해서 게터 세터 해주면 된다
}
