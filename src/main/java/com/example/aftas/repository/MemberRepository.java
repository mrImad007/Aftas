package com.example.aftas.repository;

import com.example.aftas.model.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findMemberByNum(Integer num);
    Optional<Member> findMemberByIdentityNumber(String identityNumber);
    Optional<Member> findMemberByName(String name);
    Optional<Member> findMemberByFamilyName(String familyName);
    void deleteMemberByIdentityNumber(String identityNumber);

}


