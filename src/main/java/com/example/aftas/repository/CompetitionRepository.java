package com.example.aftas.repository;

import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String> {
    Optional<Competition> findCompetitionByCode(String code);

    @Query("SELECT c.participants FROM Competition c  WHERE c.code = :code")
    List<Member> findMembersByCompetitionCode(String code);

}
