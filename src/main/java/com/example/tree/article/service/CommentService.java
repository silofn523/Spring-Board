package com.example.tree.article.service;

import com.example.tree.article.Dto.CreateCommentDto;
import com.example.tree.article.Dto.DeleteCommentDto;
import com.example.tree.article.Dto.UpdateCommentDto;
import com.example.tree.article.model.Article;
import com.example.tree.article.model.Comment;
import com.example.tree.article.repository.ArticleRepository;
import com.example.tree.article.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public List<Comment> getComment(int articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    public Comment getOneComment(int id) {
        if(!commentRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 id의 댓글이 없습니다");
        }
        return commentRepository.findById(id).orElse(null);
    }

    public Comment createComment(int articleId, CreateCommentDto dto) {
        Article article = articleRepository.findById(articleId).orElse(null);
        if (article == null) {
            throw new IllegalArgumentException("존재하지 않는 게시물 입니다");
        }
        LocalDateTime now = LocalDateTime.now();

        Comment comment = new Comment(
                0,
                dto.getContent(),
                dto.getAuthor(),
                dto.getPassword(),
                article,
                now,
                now
        );
        return commentRepository.save(comment);
    }

    public void deleteComment(int id, DeleteCommentDto dto) {
        Comment original = getOneComment(id);

        if (!original.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다");
        }
        commentRepository.deleteById(id);
    }

    public Comment updateComment(int id, UpdateCommentDto dto) {
        Comment original = getOneComment(id);

        if (!original.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다");
        }
        original.setContent(dto.getContent());
        original.setAuthor(dto.getAuthor());
        original.setUpdatedAt(LocalDateTime.now());

        return commentRepository.save(original);
    }
}
