package com.example.aftas.Services.Impl;

import com.example.aftas.Dao.MemberDao;
import com.example.aftas.exception.NotFound;
import com.example.aftas.model.Dto.RankingDto;
import com.example.aftas.model.Dto.Requests.MemberRequest;
import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.Dto.MemberDto;
import com.example.aftas.model.Entities.Ranking;
import com.example.aftas.model.mappers.Mapper;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService implements MemberDao{
    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;
    private final RankingRepository rankingRepository;
    private final Mapper<Member, MemberDto> memberMapper;
    private final Mapper<Ranking, RankingDto> rankingMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, CompetitionRepository competitionRepository, RankingRepository rankingRepository, Mapper<Member, MemberDto> memberMapper, Mapper<Ranking, RankingDto> rankingMapper) {
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;
        this.rankingRepository = rankingRepository;
        this.memberMapper = memberMapper;
        this.rankingMapper = rankingMapper;
    }
    @Override
    public List<MemberDto> getAllMembers(){
        return memberRepository.findAll().stream().map(memberMapper::mapTo).collect(Collectors.toList());
    }

    public MemberDto getMemberbyNum(Integer num) {
        Optional<Member> memberOptional = memberRepository.findMemberByNum(num);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return memberMapper.mapTo(member);
        } else {
            throw new NotFound("No member Found");
        }
    }

    public MemberDto getMemberByIdentityNumber(String identityNumber){
        Optional<Member> memberOptional = memberRepository.findMemberByIdentityNumber(identityNumber);
        if(memberOptional.isPresent()){
            Member member = memberOptional.get();
            return memberMapper.mapTo(member);
        }else {
            throw new NotFound("no member Found");
        }
    }
    public List<MemberDto> getMemberByName(String name) {
        List<Member> members = memberRepository.findAllMembersByName(name);
        List<MemberDto> memberDtos = new ArrayList<>();

        for (Member member : members) {
            memberDtos.add(memberMapper.mapTo(member));
        }

        return memberDtos;
    }
    public List<MemberDto> getMemberByFamilyName(String familyName) {
        List<Member> members = memberRepository.findAllMembersByFamilyName(familyName);
        List<MemberDto> memberDtos = new ArrayList<>();

        for (Member member : members) {
            memberDtos.add(memberMapper.mapTo(member));
        }

        return memberDtos;
    }
    public MemberDto registerMember(MemberRequest memberRequest){
        if (getMemberByIdentityNumber(memberRequest.getIdentityNumber()) == null){
            Member member = memberRequest.toModel();
            Member savedMember = memberRepository.save(member);
            return memberMapper.mapTo(savedMember);
        }else {
            System.out.println("already existing");
            return null;
        }

    }
    public MemberDto updateMember(MemberRequest memberRequest){
        Member updatedMember = memberRepository.save(memberRequest.toModel());
        return memberMapper.mapTo(updatedMember);
    }

    public void deleteMember(Member member){
        memberRepository.delete(member);
    }


    public List<RankingDto> getCompetitionMembers (String code){
        Optional<Competition> competitionOptional = competitionRepository.findCompetitionByCode(code);
        if (competitionOptional.isPresent()){
            return rankingRepository.findByCompetition(competitionOptional.get()).stream().map(rankingMapper::mapTo).collect(Collectors.toList());
        }
        return null;
    }



}
