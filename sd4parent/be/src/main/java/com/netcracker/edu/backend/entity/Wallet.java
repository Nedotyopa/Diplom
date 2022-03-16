package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float money;

//    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
//    private User owner;

    @JsonBackReference
    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.REMOVE)
    private Set<Transaction> transactions = new HashSet<>();

    public Wallet() {

    }

    public Wallet(User owner, float money) {
        this.owner = owner;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Wallet that = (Wallet) obj;
        return id == that.id &&
                Objects.equals(money, that.money) &&
                Objects.equals(owner, that.owner);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", money=" + money +
                '}';
    }
}
