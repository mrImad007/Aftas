package com.example.sb.repo;

import com.example.sb.model.Entities.Produits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Produits,Long> {
    Optional<Produits> findById(Long id);
}
