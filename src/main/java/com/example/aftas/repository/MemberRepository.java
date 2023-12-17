package com.example.aftas.repository;

import com.example.aftas.model.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findMemberByNum(Integer num);
    Optional<Member> findMemberByIdentityNumber(String identityNumber);
    List<Member> findAllMembersByName(String name);
    List<Member> findAllMembersByFamilyName(String name);
//    List<Member> findMemberByCompetitionCode(String code);

}


