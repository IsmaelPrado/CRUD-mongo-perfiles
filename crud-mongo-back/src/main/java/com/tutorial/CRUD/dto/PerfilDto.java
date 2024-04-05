package com.tutorial.CRUD.dto;

import java.util.ArrayList;
import java.util.List;

public class PerfilDto {
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

    private List<VacanteDto> vacantes;

    public PerfilDto() {
        this.vacantes = new ArrayList<>();
    }

    public PerfilDto(int id, String name, String desc, String imageURL) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.imageURL = imageURL;
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

    public List<String> getHabilidades() {
        return habilidades;
    }

    public String getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public String getEducacion() {
        return educacion;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public List<String> getIntereses() {
        return intereses;
    }

    public List<String> getReferencias() {
        return referencias;
    }

    public List<VacanteDto> getVacantes() {
        return vacantes;
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

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }

    public void setExperienciaLaboral(String experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public void setEducacion(String educacion) {
        this.educacion = educacion;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setIntereses(List<String> intereses) {
        this.intereses = intereses;
    }

    public void setReferencias(List<String> referencias) {
        this.referencias = referencias;
    }

    public void setVacantes(List<VacanteDto> vacantes) {
        this.vacantes = vacantes;
    }

}
