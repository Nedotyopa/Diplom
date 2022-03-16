package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Post;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PostService {

    Post savePost(Post product);
    Optional<Post>  getPostById(Long id);
    Iterable<Post> getAllPosts();
    void deletePost(Long id);
    Iterable<Post> getPostsByLogin(String login);
    Page<Post> getPostsByPage(int page, int quantity);
}
