package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Comment;

import java.util.Optional;

public interface CommentService {

    Comment saveComment(Comment com);
    Optional<Comment> getCommentById(Long id);
    void deleteComment(Long id);
    Iterable<Comment> getCommentsByPostId(Long id);

}
