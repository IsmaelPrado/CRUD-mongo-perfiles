package com.tutorial.CRUD.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.CRUD.entity.Perfil;
import com.tutorial.CRUD.entity.Vacante;

@Repository
public interface VacanteRepository extends MongoRepository<Vacante, Integer>{
    boolean existsByName(String name);
    Optional<Vacante> findByName(String name);
    
    List<Vacante> findByPerfilId(Perfil perfil);
} 
