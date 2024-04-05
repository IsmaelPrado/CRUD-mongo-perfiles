package com.tutorial.CRUD.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import com.tutorial.CRUD.dto.VacanteDto;
import com.tutorial.CRUD.entity.Vacante;
import com.tutorial.CRUD.service.VacanteService;
import com.tutorial.global.dto.MessageDto;
import com.tutorial.global.exceptions.AttributeException;
import com.tutorial.global.exceptions.ResourceNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/perfil/{perfilId}/vacante")
@CrossOrigin
public class VacanteController {

    @Autowired
    VacanteService vacanteService;

    @GetMapping("/all")
    public ResponseEntity<List<VacanteDto>> getAllVacantes(@PathVariable("perfilId") String perfilId)
            throws ResourceNotFoundException {
        List<VacanteDto> vacantes = vacanteService.getAllByPerfilId(perfilId);
        return ResponseEntity.ok(vacantes);
    }

    /*
     * @GetMapping
     * public ResponseEntity<List<Perfil>> getAll() {
     * return ResponseEntity.ok(perfilService.getAll());
     * }
     */

    @GetMapping("/{id}")
    public ResponseEntity<Integer> getOneVacante(@PathVariable("perfilId") int perfilId, @PathVariable("id") int id)
            throws ResourceNotFoundException {
        return ResponseEntity.ok(vacanteService.getOneByPerfilIdAndVacanteId(perfilId, id));
    }

    @PostMapping
    public ResponseEntity<MessageDto> saveVacante(@PathVariable("perfilId") int perfilId,
            @Validated @RequestBody VacanteDto dto) throws ResourceNotFoundException, AttributeException {
        // No envíes el ID de la vacante, déjalo que se genere automáticamente
        Vacante vacante = vacanteService.save(perfilId, dto);
        String message = "Vacante " + vacante.getName() + " ha sido creada";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> updateVacante(@PathVariable("perfilId") int perfilId, @PathVariable("id") int id,
            @Validated @RequestBody VacanteDto dto) throws ResourceNotFoundException, AttributeException {
        Vacante vacante = vacanteService.update(perfilId, id, dto);
        String message = "Vacante " + vacante.getName() + " ha sido actualizada";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> deleteVacante(@PathVariable("perfilId") int perfilId, @PathVariable("id") int id)
            throws ResourceNotFoundException {
        Vacante vacante = vacanteService.delete(perfilId, id);
        String message = "Vacante " + vacante.getName() + " ha sido eliminada";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
