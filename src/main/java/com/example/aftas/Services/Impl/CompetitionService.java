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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        String code = codeGenerator(competitionRequest.getLocation(), competitionRequest.getDate());
        competitionRequest.setCode(code);
        if (getCompetitionByCode(code) == null){
            Competition competition = competitionRepository.save(competitionRequest.toModel());
            return competitionMapper.mapTo(competition);
        }else{
            return null;
        }
    }

    public void deleteCompetition(String code){
        competitionRepository.deleteById(code);
    }

    public boolean competitionValidity(String code){
        Optional<Competition> competitionOptional = competitionRepository.findCompetitionByCode(code);
        if (competitionOptional.isPresent()){
            Competition competition = competitionOptional.get();

            Date competitionDate = competition.getDate();
            LocalDate localCompetitionDate = competitionDate.toLocalDate();

            if (!localCompetitionDate.isBefore(LocalDate.now())){
                Time startTime = competition.getStartTime();

                LocalDateTime competitionStartDateTime = LocalDateTime.of(localCompetitionDate, startTime.toLocalTime());
                Duration timeDifference = Duration.between(LocalDateTime.now(), competitionStartDateTime);

                if (timeDifference.toHours() >= 24){
                    if (competition.getNumberOfParticipants() <30 ){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static String codeGenerator(String city, Date date) {
        String codePrefix = city.substring(0, Math.min(city.length(), 3)).toLowerCase();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        String dateSuffix = dateFormat.format(date);

        return codePrefix + "-" + dateSuffix;
    }

    public Page<CompetitionDto> findAllPaginated(Pageable pageable){
        Page<Competition> competitionsPage = competitionRepository.findAll(pageable);
        if (!competitionsPage.isEmpty()){
            return competitionsPage.map(competitionMapper::mapTo);
        }else {
            return null;
        }
    }
}
