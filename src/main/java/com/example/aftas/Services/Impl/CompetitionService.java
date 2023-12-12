package com.example.aftas.Services.Impl;

import com.example.aftas.Dao.CompetitionDao;
import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.dto.CompetitionDto;
import com.example.aftas.model.mappers.Mapper;
import com.example.aftas.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionService implements CompetitionDao {
    public final CompetitionRepository competitionRepository;
    public final Mapper<Competition, CompetitionDto> competitionMapper;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, Mapper<Competition, CompetitionDto> competitionMapper) {
        this.competitionRepository = competitionRepository;
        this.competitionMapper = competitionMapper;
    }

    @Override
    public List<CompetitionDto> getAllCompetitions() {
        return competitionRepository.findAll().stream().map(competitionMapper::mapTo).collect(Collectors.toList());
    }

    public ResponseEntity<Object> getCompetitionByCode(String code){
        Optional<Competition> competitionOptional = competitionRepository.findCompetitionByCode(code);
        if (competitionOptional.isPresent()){
            Competition competition = competitionOptional.get();
            CompetitionDto competitionDto = competitionMapper.mapTo(competition);
            return ResponseEntity.ok(competitionDto);
        }else{
            String errorMessage = "No competition found for code: " + code;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
