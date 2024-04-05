package com.tutorial.CRUD.entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Document(collection = "vacants")
public class Vacante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @DBRef
    private Perfil perfilId;// Agregar referencia a Perfil

    public Vacante(){}
    
     // Constructor que acepta un argumento String
     public Vacante(int id, String name) {
        this.id = id;
        this.name = name;
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

    public Perfil getPerfil() {
        return perfilId;
    }

    public void setPerfilId(Perfil perfil) {
        this.perfilId = perfil;
    }

    // Método para obtener el ID del perfil asociado
    public int getPerfilId() {
        return perfilId != null ? perfilId.getId() : 0; // Devuelve el ID del perfil o 0 si el perfil es nulo
    }    

    
}
