package com.example.aftas.Dao;

import com.example.aftas.model.dto.MemberDto;

import java.util.List;

public interface MemberDao {
    List<MemberDto> getAllMembers();
}
