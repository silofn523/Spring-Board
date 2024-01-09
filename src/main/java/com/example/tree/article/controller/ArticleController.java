package com.example.tree.article.controller;

import com.example.tree.article.Dto.CreateDto;
import com.example.tree.article.Dto.DeletlDto;
import com.example.tree.article.Dto.UpdateDto;
import com.example.tree.article.model.Article;
import com.example.tree.article.service.ArticleService;
import com.example.tree.user.Entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @GetMapping("/{id}")
    public Article getPostId(@PathVariable int id) {
       return articleService.getArticleById(id);
    }

    @PostMapping
    public Article createPost(
            @AuthenticationPrincipal User user,
            @RequestBody CreateDto dto
    ) {
        return articleService.createPost(user,dto);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(
            @PathVariable int id,
            @AuthenticationPrincipal User user,
            @RequestBody(required = false) DeletlDto dto
    ) {
        articleService.deleteArticle(id,user,dto);
    }

    @PutMapping("/{id}")
    public Article updateArticle(
            @PathVariable int id,
            @AuthenticationPrincipal User user,
            @RequestBody UpdateDto dto
    ) {
        return articleService.updateArticle(id, user, dto);
    }

}
