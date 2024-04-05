package com.tutorial.CRUD.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tutorial.CRUD.dto.UsuarioDto;
import com.tutorial.CRUD.entity.Perfil;
import com.tutorial.CRUD.entity.Usuario;
import com.tutorial.CRUD.repository.UsuarioRepository;
import com.tutorial.global.exceptions.ResourceNotFoundException;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    //@Autowired
    //private PasswordEncoder passwordEncoder;

    private final String secretKey = "MiSecretKeyMuySeguraParaJWT";


    // @Autowired
    // private PasswordEncoder passwordEncoder;
    private int autoIncrement() {
        List<Usuario> usuario = usuarioRepository.findAll();
        return usuario.isEmpty() ? 1 : usuario.stream().max(Comparator.comparing(Usuario::getId)).get().getId() + 1;
    }

    public Usuario guardarUsuario(UsuarioDto usuarioDto) {
        Usuario user = new Usuario();
        user.setId(autoIncrement());
        user.setUsername(usuarioDto.getUsername());
        user.setPassword(usuarioDto.getPassword());
        Set<String> roles = usuarioDto.getRoles();
        if (roles == null || roles.isEmpty()) {
            roles = new HashSet<>();
            roles.add("ADMIN"); // Rol por defecto si no se especifica ningún rol
        }
        user.setRoles(roles);
        return usuarioRepository.save(user);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario eliminarUsuarioPorId(int id) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
        usuarioRepository.delete(usuario);
        return usuario;
    }

    public Usuario obteneUsuarioPorId(int id) throws ResourceNotFoundException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found"));
    }

    public UsuarioDto convertirUsuarioAUsuarioDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsername(usuario.getUsername());
        usuarioDto.setPassword(usuario.getPassword());
        usuarioDto.setRoles(usuario.getRoles());
        return usuarioDto;
    }

    public Usuario login(String username, String password) throws Exception {
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }

        // Verifica la contraseña utilizando el PasswordEncoder
        if (!usuario.getPassword().equals(password)) {
            throw new Exception("Contraseña incorrecta");
        }

        return usuario;
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 864000000); // Token expira en 10 días (en milisegundos)
    
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
    

    public boolean validateToken(String token) {
        try {
             Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

}
