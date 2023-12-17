package com.example.aftas.repository;

import com.example.aftas.model.Dto.HuntingDto;
import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Entities.Hunting;
import com.example.aftas.model.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Integer> {
    List<Hunting> findHuntingByCompetitionCode(String code);
    List<Hunting> findByMemberNumAndCompetitionCode(Integer memberNum, String competitionCode);
}


