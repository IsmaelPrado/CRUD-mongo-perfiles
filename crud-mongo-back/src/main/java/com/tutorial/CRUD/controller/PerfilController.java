package com.tutorial.CRUD.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.CRUD.dto.PerfilDto;
import com.tutorial.CRUD.entity.Perfil;
import com.tutorial.CRUD.entity.Vacante;
import com.tutorial.CRUD.service.PerfilService;
import com.tutorial.global.dto.MessageDto;
import com.tutorial.global.exceptions.AttributeException;
import com.tutorial.global.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/perfil")
@CrossOrigin
public class PerfilController {

    @Autowired
    PerfilService perfilService;

    @GetMapping
    public ResponseEntity<List<Perfil>> getAll() {
        List<Perfil> perfiles = perfilService.getAll();
        for (Perfil perfil : perfiles) {
            List<Integer> vacanteIds = perfil.getVacanteIds(); // Obtener solo los IDs de las vacantes
            perfil.setVacantes(null); // Establecer la lista de vacantes como null para evitar la serialización recursiva
            // No establecer los IDs de las vacantes en el perfil, ya que no existe el método setVacanteIds(List<Integer>)
        }
        return ResponseEntity.ok(perfiles);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Perfil> getOne(@PathVariable("id") int id) throws ResourceNotFoundException {
        Perfil perfil = perfilService.getOne(id);
        List<Integer> vacantes = perfilService.getVacantesByPerfilId(id); // Obtener vacantes por perfilId
        perfil.setVacantes(vacantes); // Establecer las vacantes en el perfil
        return ResponseEntity.ok(perfil);
    }

    @PostMapping
    public ResponseEntity<MessageDto> save(@Validated @RequestBody PerfilDto dto) throws AttributeException {
        Perfil perfil = perfilService.save(dto);
        String message = "Perfil " + perfil.getName() + " have been saved";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> update(@PathVariable("id") int id, @Validated @RequestBody PerfilDto dto)
            throws ResourceNotFoundException, AttributeException {
        Perfil perfil = perfilService.update(id, dto);
        String message = "Perfil " + perfil.getName() + " have been updated";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        Perfil perfil = perfilService.delete(id);
        String message = "Perfil " + perfil.getName() + " have been deleted";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}