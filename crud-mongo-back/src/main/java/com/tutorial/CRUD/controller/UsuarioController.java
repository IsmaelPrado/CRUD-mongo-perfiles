package com.tutorial.CRUD.controller;

import com.tutorial.CRUD.dto.PerfilDto;
import com.tutorial.CRUD.dto.UsuarioDto;
import com.tutorial.CRUD.entity.Perfil;
import com.tutorial.CRUD.entity.Usuario;
import com.tutorial.CRUD.service.UsuarioService;
import com.tutorial.global.dto.MessageDto;
import com.tutorial.global.exceptions.AttributeException;
import com.tutorial.global.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuarioGuardado = usuarioService.guardarUsuario(usuarioDto);
        return new ResponseEntity<>(usuarioGuardado, HttpStatus.CREATED);
    }

    @GetMapping("buscar/{id}")
    public ResponseEntity<Usuario> getOne(@PathVariable("id") int id) throws ResourceNotFoundException {
        Usuario usuario = usuarioService.obteneUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        if (!usuarios.isEmpty()) {
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<MessageDto> eliminarUsuario(@PathVariable("id") int id) throws ResourceNotFoundException {
        Usuario usuario = usuarioService.eliminarUsuarioPorId(id);
        String message = "Usuario " + usuario.getUsername() + " have been deleted";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<MessageDto> actualizarUsuario(@PathVariable("id") int id, @RequestBody UsuarioDto usuarioDto)
            throws ResourceNotFoundException {
        Usuario usuarioExistente = usuarioService.obteneUsuarioPorId(id);
        if (usuarioExistente != null) {
            usuarioExistente.setUsername(usuarioDto.getUsername());
            usuarioExistente.setPassword(usuarioDto.getPassword());
            usuarioExistente.setRoles(usuarioDto.getRoles());

            // Convertir Usuario a UsuarioDto
            UsuarioDto usuarioDtoActualizado = usuarioService.convertirUsuarioAUsuarioDto(usuarioExistente);

            // Llamar al método guardarUsuario con el UsuarioDto actualizado
            Usuario usuarioActualizado = usuarioService.guardarUsuario(usuarioDtoActualizado);

            String message = "Usuario " + usuarioActualizado.getUsername() + " have been updated";
            return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody(required = false) UsuarioDto usuarioDto) {
        try {
            // Verificar que se proporcione un objeto UsuarioDto y que los campos no sean
            // nulos
            if (usuarioDto != null && usuarioDto.getUsername() != null && usuarioDto.getPassword() != null) {
                // Intentar realizar el inicio de sesión y generar el token
                Usuario usuario = usuarioService.login(usuarioDto.getUsername(), usuarioDto.getPassword());
                String token = usuarioService.generateToken(usuarioDto.getUsername());
                return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
            } else {
                // Si no se proporcionan credenciales válidas, devolver un error
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Credenciales inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Inicio de sesión fallido");
        }
    }

}
