package com.example.aftas.Dao;

import com.example.aftas.model.Dto.CompetitionDto;
import com.example.aftas.model.Entities.Competition;

import java.util.List;

public interface CompetitionDao {
    List<CompetitionDto> getAllCompetitions();
}
