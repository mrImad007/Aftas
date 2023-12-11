package com.example.aftas.service.Impl;

import com.example.aftas.Dao.MemberDao;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.dto.MemberDto;
import com.example.aftas.model.mappers.Mapper;
import com.example.aftas.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<MemberDto> getAllMembers(){
        System.out.println(memberRepository.findAll().stream().map(memberMapper::mapTo).collect(Collectors.toList()));
        return memberRepository.findAll().stream().map(memberMapper::mapTo).collect(Collectors.toList());
    }
}
