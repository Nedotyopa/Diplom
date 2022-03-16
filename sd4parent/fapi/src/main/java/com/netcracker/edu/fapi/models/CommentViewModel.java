package com.netcracker.edu.fapi.models;

public class CommentViewModel {

    private Long id;
    private UserViewModel user;
    private PostViewModel post;
    private String text;
    private String date;

    public CommentViewModel() {
    }

    public CommentViewModel(Long id, UserViewModel user, PostViewModel post, String text, String date) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.text = text;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }

    public PostViewModel getPost() {
        return post;
    }

    public void setPost(PostViewModel post) {
        this.post = post;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
