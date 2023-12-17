package com.example.aftas.repository;

import com.example.aftas.model.Entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
    Optional<Level> findLevelByDescription(String description);
    Optional<Level> findLevelByPoints(Integer points);

}
