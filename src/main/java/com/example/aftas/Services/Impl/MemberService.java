package com.example.aftas.Services.Impl;

import com.example.aftas.Dao.MemberDao;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.dto.MemberDto;
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

    public ResponseEntity<Object> getMemberbyNum(Integer num) {
        Optional<Member> memberOptional = memberRepository.findMemberByNum(num);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            MemberDto memberDto = memberMapper.mapTo(member);
            return ResponseEntity.ok(memberDto);
        } else {
            String errorMessage = "No member found for number: " + num;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
