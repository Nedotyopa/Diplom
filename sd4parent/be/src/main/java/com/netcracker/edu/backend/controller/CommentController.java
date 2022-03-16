package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Comment;
import com.netcracker.edu.backend.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Comment> getCommentById(@PathVariable(name = "id") Long id) {
        Optional<Comment> com = commentService.getCommentById(id);

        if(com.isPresent()){
            return ResponseEntity.ok(com.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Comment saveComment(@RequestBody Comment com) {
        return commentService.saveComment(com);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@PathVariable(name = "id") Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Iterable<Comment> getCommentsByPostId(@RequestParam("post") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

}
