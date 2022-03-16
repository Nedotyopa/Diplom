package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "package")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private int discount;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "packagesposts", joinColumns = { @JoinColumn(name = "package_id") }, inverseJoinColumns = { @JoinColumn(name = "post_id") })
    private Set<Post> posts = new HashSet<>();

    public Package() {
    }

    public Package(String title, String description, int discount, Set<Post> posts) {
        this.title = title;
        this.description = description;
        this.discount = discount;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Package{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                '}';
    }
}
