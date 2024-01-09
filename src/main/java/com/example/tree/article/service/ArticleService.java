package com.example.tree.article.service;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.example.tree.article.Dto.CreateDto;
import com.example.tree.article.Dto.DeletlDto;
import com.example.tree.article.Dto.UpdateDto;
import com.example.tree.article.model.Article;
import com.example.tree.article.repository.ArticleRepository;
import com.example.tree.user.Entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private ArticleRepository articleRepository;

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(int id) {
         return articleRepository.findById(id)
                 .orElseThrow(() -> new IllegalArgumentException("잘못된 Post ID 입니다."));
    }

    public Article createPost(User user ,CreateDto dto) {
    //    return articleRepository.save(dto);
        LocalDateTime now = LocalDateTime.now();

        Article article = new Article(
                0,
                dto.getTitle(),
                dto.getContent(),
                dto.getAuthor(),
                dto.getPassword(),
                user,
                new ArrayList<>(),
                now,
                now
        );
        return articleRepository.save(article);
    }

    public void deleteArticle(int id,User user , DeletlDto dto) {
        Article post = getArticleById(id);

        if (post.getUser() != null) {
            if (user == null) {
                throw new IllegalArgumentException("로그인이 필요합니다");
            }

            if (!post.getUser().getId().equals(user.getId())) {
                throw new IllegalArgumentException("내 글만 삭제 가능합니다");
            }
        } else {
            if (!post.getPassword().equals(dto.getPassword())) {
                throw new IllegalArgumentException("잘못된 비번입니다");
            }
        }

        articleRepository.deleteById(id);
    }

    /*public Article updateArticle(int id, Article article) {
        getArticleById(id);
        article.setId(id);
        return articleRepository.save(article);
    }*/

    public Article updateArticle(int id, User user ,UpdateDto dto) {
        Article original = getArticleById(id);
        if (original.getUser() != null) {
            if (user == null) {
                throw new IllegalArgumentException("로그인이 필요합니다");
            }

            if (!original.getUser().getId().equals(user.getId())) {
                throw new IllegalArgumentException("내 글만 수정 가능합니다");
            }
        } else {
            if (!original.getPassword().equals(dto.getPassword())) {
                throw new IllegalArgumentException("잘못된 비번입니다");
            }
        }

        original.setTitle(dto.getTitle());
        original.setContent(dto.getContent());
        original.setAuthor(dto.getAuthor());
        original.setUpdatedAt(LocalDateTime.now());

        return articleRepository.save(original);
    }
}
