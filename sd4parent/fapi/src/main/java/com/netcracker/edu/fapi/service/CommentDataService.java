package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.CommentViewModel;

import java.util.List;

public interface CommentDataService {

    CommentViewModel saveComment(CommentViewModel com);
    CommentViewModel getCommentById(Long id);
    void deleteComment(Long id);
    List<CommentViewModel> getCommentsByPostId(Long id);

}
