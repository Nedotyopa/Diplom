package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Comment;
import com.netcracker.edu.backend.repository.CommentRepository;
import com.netcracker.edu.backend.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.netcracker.edu.backend.repository.specification.CommentSpecification.commentsFindByPostId;

@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository repository;

    @Override
    public Comment saveComment(Comment com) {
        return repository.save(com);
    }

    @Override
    public void deleteComment(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Comment> getCommentsByPostId(Long id) {
        return repository.findAll(commentsFindByPostId(id));
    }

}
