package com.example.aftas.repository;

import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.Entities.Ranking;
import com.example.aftas.model.Entities.RankingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, RankingId> {
//    Optional<Ranking> findRankingById(RankingId id);

    List<Ranking> findRankingByCompetitionCode(String code);
    List<Ranking> findByCompetition(Competition competition);
    Optional<Ranking> findRankingByMemberNum(Integer num);

}
