package com.example.springProject.springProject.entities;


import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity                                   //0.0.001->defines entity
@Table(name ="User")                      //0.0.002->for changing tables in database


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)        //0.0.003->for generating auto value
    private int id;

    @Column(length = 20)                       //0.0.004->for changing field
    private String name;
    @Column(unique = true)                     //0.0.005->changing field
    private String email;
    private String password;
    private String role;
    private String imageUrl;



    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")         // for specifing database relation // CascadeType.ALL will help like when user will be deleted do will delete automatic
    //visit: https://howtodoinjava.com/hibernate/how-to-define-association-mappings-between-hibernate-entities/

    public List<ToDo> todo;
    {
        todo = new ArrayList<>();
    }

    public List<ToDo> getTodo() {
        return todo;
    }

    public void setTodo(List<ToDo> todo) {
        this.todo = todo;
    }
}

