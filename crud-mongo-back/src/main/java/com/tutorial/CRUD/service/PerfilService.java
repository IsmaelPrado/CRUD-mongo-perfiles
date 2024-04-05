package com.tutorial.CRUD.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tutorial.CRUD.dto.PerfilDto;
import com.tutorial.CRUD.dto.VacanteDto;
import com.tutorial.CRUD.entity.Perfil;
import com.tutorial.CRUD.entity.Vacante;
import com.tutorial.CRUD.repository.PerfilRepository;
import com.tutorial.CRUD.repository.VacanteRepository;
import com.tutorial.global.exceptions.AttributeException;
import com.tutorial.global.exceptions.ResourceNotFoundException;

@Service
public class PerfilService {

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    VacanteRepository vacanteRepository;

    public List<Perfil> getAll() {
        return perfilRepository.findAll();
    }

    public Perfil getOne(int id) throws ResourceNotFoundException {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil not found"));
    }

    public List<Integer> getVacantesByPerfilId(int perfilId) throws ResourceNotFoundException {
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil not found"));
        return perfil.getVacanteIds(); // Devuelve las vacantes asociadas al perfil
    }
    

    public Perfil save(PerfilDto dto) throws AttributeException {
        if (perfilRepository.existsByName(dto.getName())) {
            throw new AttributeException("Name already in use");
        }
        
        int id = autoIncrement();
        Perfil perfil = new Perfil(
            id,
            dto.getName(),
            dto.getDesc(),
            dto.getImageURL(),
            dto.getHabilidades(),
            dto.getExperienciaLaboral(),
            dto.getEducacion(),
            dto.getIdiomas(),
            dto.getUbicacion(),
            dto.getDisponibilidad(),
            dto.getIntereses(),
            dto.getReferencias()
        );
        
        return perfilRepository.save(perfil);
    }
    

    public Perfil update(int id, PerfilDto dto) throws ResourceNotFoundException, AttributeException {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil not found"));
    
        // Verifica si el nuevo nombre ya existe en otro perfil
        if (perfilRepository.existsByName(dto.getName()) && perfilRepository.findByName(dto.getName()).get().getId() != id) {
            throw new AttributeException("Name already in use");
        }
    
        // Actualiza todos los campos del perfil con los valores proporcionados en el DTO
        perfil.setName(dto.getName());
        perfil.setDesc(dto.getDesc());
        perfil.setImageURL(dto.getImageURL());
        perfil.setHabilidades(dto.getHabilidades());
        perfil.setExperienciaLaboral(dto.getExperienciaLaboral());
        perfil.setEducacion(dto.getEducacion());
        perfil.setIdiomas(dto.getIdiomas());
        perfil.setUbicacion(dto.getUbicacion());
        perfil.setDisponibilidad(dto.getDisponibilidad());
        perfil.setIntereses(dto.getIntereses());
        perfil.setReferencias(dto.getReferencias());
    
        // Guarda el perfil actualizado en la base de datos
        return perfilRepository.save(perfil);
    }
    

    public Perfil delete(int id) throws ResourceNotFoundException {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil not found"));
        perfilRepository.delete(perfil);
        return perfil;
    }

    @Transactional
    public Perfil agregarVacante(int perfilId, VacanteDto vacanteDto) throws ResourceNotFoundException {
        Perfil perfil = getOne(perfilId);
        Vacante vacante = new Vacante(perfilId, vacanteDto.getName());
        vacante.setPerfilId(perfil); // Asignamos la relación bidireccional
        vacanteRepository.save(vacante); // Guardamos la vacante
        perfil.getVacanteIds().addAll((Collection<? extends Integer>) vacante); // Agregamos la vacante al perfil
        return perfilRepository.save(perfil); // Guardamos el perfil actualizado
    }

    @Transactional
    public Perfil eliminarVacante(int perfilId, int vacanteId) throws ResourceNotFoundException {
        Perfil perfil = getOne(perfilId);
        Vacante vacante = vacanteRepository.findById(vacanteId)
                .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found"));

        perfil.getVacanteIds().remove(vacante); // Removemos la vacante del perfil
        vacante.setPerfilId(null); // Quitamos la relación bidireccional
        vacanteRepository.delete(vacante); // Borramos la vacante

        return perfilRepository.save(perfil); // Guardamos el perfil actualizado
    }

    @Transactional
    public Perfil actualizarVacante(int perfilId, int vacanteId, VacanteDto vacanteDto) throws ResourceNotFoundException {
        Perfil perfil = getOne(perfilId);
        Vacante vacante = vacanteRepository.findById(vacanteId)
                .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found"));

        vacante.setName(vacanteDto.getName()); // Actualizamos el nombre de la vacante
        vacanteRepository.save(vacante); // Guardamos la vacante actualizada

        return perfilRepository.save(perfil); // Guardamos el perfil actualizado
    }

    private int autoIncrement() {
        List<Perfil> perfiles = perfilRepository.findAll();
        return perfiles.isEmpty() ? 1 : perfiles.stream().max(Comparator.comparing(Perfil::getId)).get().getId() + 1;
    }
}
