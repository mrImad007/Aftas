package com.example.aftas.model.mappers.Impl;

import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.dto.MemberDto;
import com.example.aftas.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper implements Mapper<Member, MemberDto> {
    private final ModelMapper modelMapper;

    public MemberMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public MemberDto mapTo(Member member) {
        return modelMapper.map(member, MemberDto.class);
    }

    @Override
    public Member mapFrom(MemberDto memberDto) {
        return modelMapper.map(memberDto, Member.class);
    }
}
