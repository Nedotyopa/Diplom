package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private float price;
    private int discount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL)
    Set<Subscription> subscriptions = new HashSet<Subscription>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "posts")
//    @JoinTable(name = "packagesposts", joinColumns = { @JoinColumn(name = "post_id") }, inverseJoinColumns = { @JoinColumn(name = "package_id") })
    private Set<Package> packages = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();

    public Post(String title, String description, float price, int discount) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.discount = discount;
    }

    public Post() {

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Post that = (Post) obj;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, discount);
    }

    @Override
    public String toString() {
        return "Post { id: " + id +
        ", title: " + title +
        ", description: " + description +
        ", price: " + price +
        ", discount: " + discount + " }";
    }

}
