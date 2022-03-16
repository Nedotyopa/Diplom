package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.CommentViewModel;
import com.netcracker.edu.fapi.service.CommentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/c")
public class CommentDataController {

    @Autowired
    CommentDataService commentDataService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CommentViewModel> saveComment(@RequestBody CommentViewModel com) {
        if(com != null) {
            return ResponseEntity.ok(commentDataService.saveComment(com));
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CommentViewModel> getCommentById(@PathVariable String id) {
        return ResponseEntity.ok(commentDataService.getCommentById(Long.valueOf(id)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable String id) {
        commentDataService.deleteComment(Long.valueOf(id));
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<List<CommentViewModel>> getCommentsByPostId(@RequestParam("post") String postId) {
        return ResponseEntity.ok(commentDataService.getCommentsByPostId(Long.valueOf(postId)));
    }

}
