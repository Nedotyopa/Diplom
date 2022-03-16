package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)

public class SubscriptionViewModel {

    private long id;
    private UserViewModel user;
    private PostViewModel post;
    private float cost;
    private int duration;
    private boolean status;

    public SubscriptionViewModel() {
    }

    public SubscriptionViewModel(long id, UserViewModel user, PostViewModel post, float cost, int duration, boolean status) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.cost = cost;
        this.duration = duration;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
