package com.example.tree.article.controller;

import com.example.tree.article.Dto.CreateCommentDto;
import com.example.tree.article.Dto.DeleteCommentDto;
import com.example.tree.article.Dto.UpdateCommentDto;
import com.example.tree.article.model.Comment;
import com.example.tree.article.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class CommentController {
    private CommentService commentService;

    @GetMapping("/{articleId}/comments")
    public List<Comment> getComment(@PathVariable int articleId) {
        return commentService.getComment(articleId);
    }

    @PostMapping("/{articleId}/comments")
    public Comment createComment(@PathVariable int articleId, @RequestBody CreateCommentDto dto) {
        return commentService.createComment(articleId ,dto);
    }

    @DeleteMapping("/comments/{id}")
    private void deleteComment(@PathVariable int id, @RequestBody DeleteCommentDto dto) {
        commentService.deleteComment(id, dto);
    }

    @PutMapping("/comments/{id}")
    private Comment updateComment(@PathVariable int id, @RequestBody UpdateCommentDto dto) {
        Comment comment = commentService.updateComment(id,dto);
        return comment;
    }
}
