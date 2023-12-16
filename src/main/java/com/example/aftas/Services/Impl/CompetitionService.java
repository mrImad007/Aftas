package com.example.aftas.Services.Impl;

import com.example.aftas.Dao.CompetitionDao;
import com.example.aftas.model.Dto.MemberDto;
import com.example.aftas.model.Dto.Requests.CompetitionRequest;
import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Dto.CompetitionDto;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.mappers.Mapper;
import com.example.aftas.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionService implements CompetitionDao {
    public final CompetitionRepository competitionRepository;
    public final Mapper<Competition, CompetitionDto> competitionMapper;

    public final Mapper<Member, MemberDto> memberMapper;


    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, Mapper<Competition, CompetitionDto> competitionMapper,Mapper<Member, MemberDto> memberMapper) {
        this.competitionRepository = competitionRepository;
        this.competitionMapper = competitionMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    public List<CompetitionDto> getAllCompetitions() {
        return competitionRepository.findAll().stream().map(competitionMapper::mapTo).collect(Collectors.toList());
    }

    public CompetitionDto getCompetitionByCode(String code){
        Optional<Competition> competitionOptional = competitionRepository.findCompetitionByCode(code);
        if (competitionOptional.isPresent()) {
            Competition competition = competitionOptional.get();
            return competitionMapper.mapTo(competition);
        }
        return null;
    }

    public CompetitionDto save(CompetitionRequest competitionRequest) {
        Competition competition = competitionRepository.save(competitionRequest.toModel());
        return competitionMapper.mapTo(competition);
    }

    public void deleteCompetition(String id){
        competitionRepository.deleteById(id);
    }
}
