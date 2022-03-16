package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.CommentViewModel;
import com.netcracker.edu.fapi.service.CommentDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CommentDataServiceImpl implements CommentDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public CommentViewModel saveComment(CommentViewModel com) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/comments", com, CommentViewModel.class).getBody();
    }

    @Override
    public CommentViewModel getCommentById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/comments/" + id, CommentViewModel.class);
    }

    @Override
    public void deleteComment(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/comments/" + id);
    }

    @Override
    public  List<CommentViewModel> getCommentsByPostId(Long postId) {
        RestTemplate restTemplate = new RestTemplate();
        CommentViewModel[] commentViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/comments/find?post=" + postId, CommentViewModel[].class);
        return commentViewModelResponse == null ? Collections.emptyList() : Arrays.asList(commentViewModelResponse);
    }

}
