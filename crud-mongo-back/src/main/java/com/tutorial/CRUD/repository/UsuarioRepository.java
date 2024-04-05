package com.tutorial.CRUD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.CRUD.entity.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
    boolean existsByUsername(String username);
}
