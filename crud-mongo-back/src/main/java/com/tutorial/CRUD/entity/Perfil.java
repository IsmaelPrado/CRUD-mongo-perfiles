package com.tutorial.CRUD.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.stream.Collectors; // Importa la clase Collectors

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import java.util.ArrayList;

@Document(collection = "perfils")
public class Perfil {

    @Id
    private int id;
    private String name;
    private String desc;
    private String imageURL;
    private List<String> habilidades;
    private String experienciaLaboral;
    private String educacion;
    private List<String> idiomas;
    private String ubicacion;
    private String disponibilidad;
    private List<String> intereses;
    private List<String> referencias;

    @OneToMany(mappedBy = "perfilId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference // Ignora la serialización de la relación en esta dirección
    private List<Vacante> vacantes; // Declaración correcta de la lista de vacantes

    public Perfil() {
        this.vacantes = new ArrayList<>();
    }

    public Perfil(int id, String name, String desc, String imageURL,
            List<String> habilidades, String experienciaLaboral, String educacion,
            List<String> idiomas, String ubicacion, String disponibilidad,
            List<String> intereses, List<String> referencias) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.imageURL = imageURL;
        this.habilidades = habilidades;
        this.experienciaLaboral = experienciaLaboral;
        this.educacion = educacion;
        this.idiomas = idiomas;
        this.ubicacion = ubicacion;
        this.disponibilidad = disponibilidad;
        this.intereses = intereses;
        this.referencias = referencias;
        this.vacantes = new ArrayList<>();
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageURL() {
        return imageURL;
    }

    // Método para obtener solo los IDs de las vacantes

    public List<Integer> getVacanteIds() {
        return vacantes.stream().map(Vacante::getId).collect(Collectors.toList());
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }

    public String getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(String experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public String getEducacion() {
        return educacion;
    }

    public void setEducacion(String educacion) {
        this.educacion = educacion;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public List<String> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<String> intereses) {
        this.intereses = intereses;
    }

    public List<String> getReferencias() {
        return referencias;
    }

    public void setReferencias(List<String> referencias) {
        this.referencias = referencias;
    }

    public List<Vacante> getVacantes() {
        return vacantes;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setVacantes(List<Integer> vacantes2) {
        this.vacantes = vacantes;
    }

    // Método para eliminar una vacante
    public void removeVacante(Vacante vacante) {
        if (vacantes != null) {
            vacantes.remove(vacante);
        }
    }
}
