package com.tutorial.CRUD.repository;

import com.tutorial.CRUD.entity.Product;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
    boolean existsByName(String name);
    Optional<Product> findByName(String name);
}