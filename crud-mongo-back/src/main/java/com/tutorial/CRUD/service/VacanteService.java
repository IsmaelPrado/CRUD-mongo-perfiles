package com.tutorial.CRUD.service;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tutorial.CRUD.dto.PerfilDto;
import com.tutorial.CRUD.dto.VacanteDto;
import com.tutorial.CRUD.entity.Perfil;
import com.tutorial.CRUD.entity.Vacante;
import com.tutorial.CRUD.repository.PerfilRepository;
import com.tutorial.CRUD.repository.VacanteRepository;
import com.tutorial.global.exceptions.AttributeException;
import com.tutorial.global.exceptions.ResourceNotFoundException;

@Service
public class VacanteService {

    @Autowired
    VacanteRepository vacanteRepository;

    @Autowired
    PerfilRepository perfilRepository;

    public List<VacanteDto> getAllByPerfilId(String perfilId) throws ResourceNotFoundException {
        Integer perfilIdInteger = Integer.parseInt(perfilId);
        Perfil perfil = perfilRepository.findById(perfilIdInteger)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil not found"));

        // Obtener todas las vacantes asociadas al perfil
        List<Vacante> vacantes = vacanteRepository.findByPerfilId(perfil);

        // Mapear las vacantes a VacanteDto
        return vacantes.stream()
                .map(vacante -> new VacanteDto(vacante.getId(),vacante.getName(), vacante.getPerfil().getId())) // Corregido a getPerfilId().getId()
                .collect(Collectors.toList());
    }

    public Vacante save(int perfilId, VacanteDto dto) throws ResourceNotFoundException, AttributeException {
        Integer perfilIdInteger = perfilId;
        Perfil perfil = perfilRepository.findById(perfilIdInteger)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil not found"));

        // Verificar si el nombre de la vacante ya está en uso
        if (vacanteRepository.existsByName(dto.getName())) {
            throw new AttributeException("Name already in use");
        }

        // Obtener el nuevo ID utilizando autoIncrement()
        int newVacanteId = autoIncrement();

        // Crear la nueva vacante con el nuevo ID
        Vacante vacante = new Vacante(newVacanteId, dto.getName());

        // Establecer la relación bidireccional
        vacante.setPerfilId(perfil);

        // Guardar la vacante
        vacanteRepository.save(vacante);

        // Agregar el ID de la vacante al perfil
        perfil.getVacanteIds().add(newVacanteId);

        // Guardar el perfil actualizado
        perfilRepository.save(perfil);

        return vacante;
    }

    public Vacante update(int perfilId, int vacanteId, VacanteDto dto)
            throws ResourceNotFoundException, AttributeException {
        Integer perfilIdInteger = perfilId;
        Perfil perfil = perfilRepository.findById(perfilIdInteger)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil not found"));
        Integer vacanteIdInteger = perfil.getVacanteIds().stream()
                .filter(id -> id == vacanteId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Vacante not found"));
        Vacante vacante = vacanteRepository.findById(vacanteIdInteger)
                .orElseThrow(() -> new ResourceNotFoundException("Vacante not found"));
        if (vacanteRepository.existsByName(dto.getName())
                && vacanteRepository.findByName(dto.getName()).get().getId() != vacanteId)
            throw new AttributeException("Name already in use");
        vacante.setName(dto.getName());
        vacanteRepository.save(vacante); // Guardar la vacante actualizada
        return vacante;
    }

    public Vacante delete(int perfilId, int vacanteId) throws ResourceNotFoundException {
        Integer perfilIdInteger = perfilId;
        Perfil perfil = perfilRepository.findById(perfilIdInteger)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil not found"));
        Integer vacanteIdInteger = perfil.getVacanteIds().stream()
                .filter(id -> id == vacanteId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Vacante not found"));
        Vacante vacante = vacanteRepository.findById(vacanteIdInteger)
                .orElseThrow(() -> new ResourceNotFoundException("Vacante not found :/"));
        perfil.removeVacante(vacante); // Remover la vacante del perfil
        vacante.setPerfilId(null); // Quitamos la relación bidireccional
        vacanteRepository.delete(vacante); // Borramos la vacante
        perfilRepository.save(perfil); // Guardamos el perfil actualizado
        return vacante;
    }

    public Integer getOneByPerfilIdAndVacanteId(int perfilId, int vacanteId) throws ResourceNotFoundException {
        Integer perfilIdInteger = perfilId;
        Perfil perfil = perfilRepository.findById(perfilIdInteger)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil not found"));
        if (!perfil.getVacanteIds().contains(vacanteId)) {
            throw new ResourceNotFoundException("Vacante not found in the specified perfil");
        }
        return vacanteId;
    }

    private int autoIncrement() {
        List<Vacante> vacantes = vacanteRepository.findAll();
        return vacantes.isEmpty() ? 1 : vacantes.stream().max(Comparator.comparing(Vacante::getId)).get().getId() + 1;
    }
    
}
