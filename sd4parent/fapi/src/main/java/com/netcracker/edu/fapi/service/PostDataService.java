package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.PostViewModel;

import java.util.List;

public interface PostDataService {

    List<PostViewModel> getAll();
    PostViewModel getPostById(Long id);
    PostViewModel savePost(PostViewModel product);
    void deletePost(Long id);
    List<PostViewModel> getPostsByLogin(String login);
    List<PostViewModel> getPostsByPage(int page, int quantity);
    int getTotalPages(int quantity);

}
