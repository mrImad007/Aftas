package com.example.sb.repo;

import com.example.sb.model.Entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Categories,Long> {
    Optional<Categories> findById(Long id);

}
