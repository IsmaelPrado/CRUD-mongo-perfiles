package com.tutorial.CRUD.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "users")
public class Usuario {
    @Id
    private int id;
    private String username;
    private String password;
    private Set<String> roles;

    // Constructor, getters y setters

    public Usuario() {
    }

    public Usuario(int id, String username, String password, Set<String> roles) {
        this.id= id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

