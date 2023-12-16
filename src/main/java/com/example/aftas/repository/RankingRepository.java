package com.example.aftas.repository;

import com.example.aftas.model.Entities.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Integer> {
    Optional<Ranking> findRankingById(Integer id);
    List<Ranking> findRankingByCompetitionCode(String code);

}
