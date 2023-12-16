package com.example.aftas.Services.Impl;

import com.example.aftas.Dao.MemberDao;
import com.example.aftas.model.Dto.Requests.MemberRequest;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.Dto.MemberDto;
import com.example.aftas.model.mappers.Mapper;
import com.example.aftas.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService implements MemberDao{
    private final MemberRepository memberRepository;
    private final Mapper<Member, MemberDto> memberMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, Mapper<Member, MemberDto> memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
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
            return null;
        }
    }

    public MemberDto getMemberByIdentityNumber(String identityNumber){
        Optional<Member> memberOptional = memberRepository.findMemberByIdentityNumber(identityNumber);
        if(memberOptional.isPresent()){
            Member member = memberOptional.get();
            return memberMapper.mapTo(member);
        }else {
            return null;
        }
    }
    public MemberDto getMemberByName(String name) {
        Optional<Member> memberOptional = memberRepository.findMemberByName(name);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return memberMapper.mapTo(member);
        } else {
            return null;
        }
    }
    public MemberDto getMemberByFamilyName(String familyName) {
        Optional<Member> memberOptional = memberRepository.findMemberByFamilyName(familyName);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return memberMapper.mapTo(member);
        } else {
            return null;
        }
    }
    public MemberDto registerMember(MemberRequest memberRequest){
        Member member = memberRequest.toModel();
        Member savedMember = memberRepository.save(member);
        return memberMapper.mapTo(savedMember);
    }
    public MemberDto updateMember(MemberRequest memberRequest){
        Member member = memberRequest.toModel();
        Member updatedMember = memberRepository.save(member);
        return memberMapper.mapTo(updatedMember);
    }

    public void deleteMember(Member member){
        memberRepository.delete(member);
    }
}
