package com.example.aftas.repository;

import com.example.aftas.model.Entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    List<Hunting> findHuntingByCompetitionCode(String code);
}
