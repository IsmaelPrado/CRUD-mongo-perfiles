package com.tutorial.CRUD.dto;

import com.tutorial.CRUD.entity.Perfil;

public class VacanteDto {

    private int id;
    private String name;
    private int perfilId;

   

    // Constructor
    public VacanteDto(int id, String name, int perfilId) {
        this.id = id;
        this.name = name;
        this.perfilId = perfilId;
    }
    public VacanteDto(){}

    // Getters y setters

    public int getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(int perfil) {
        this.perfilId = perfilId;
    }
}
