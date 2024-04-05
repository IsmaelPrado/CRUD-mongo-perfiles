package com.tutorial.CRUD.dto;

import java.util.Set;

public class UsuarioDto {
    private int id;
    private String username;
    private String password;
    private Set<String> roles;

    // Constructores, getters y setters

    public UsuarioDto() {
    }

    public UsuarioDto(int id, String username, String password, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Getters y Setters
    public int getId(){
        return id;
    }

    public void setId(){
        this.id=id;
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

