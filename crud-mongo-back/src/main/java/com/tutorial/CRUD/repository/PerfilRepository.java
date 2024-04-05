package com.tutorial.CRUD.repository;

import com.tutorial.CRUD.entity.Perfil;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends MongoRepository<Perfil, Integer> {
    boolean existsByName(String name);
    Optional<Perfil> findByName(String name);
}