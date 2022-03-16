package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PackageViewModel {

    private long id;
    private String title;
    private String description;
    private int discount;
    private Set<PostViewModel> posts;

    public PackageViewModel() {
    }

    public PackageViewModel(long id, String title, String description, int discount, Set<PostViewModel> posts) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.discount = discount;
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Set<PostViewModel> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostViewModel> posts) {
        this.posts = posts;
    }
}
