package com.example.aftas.repository;

import com.example.aftas.model.Entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, String> {
    Optional<Competition> findCompetitionByCode(String code);
}
