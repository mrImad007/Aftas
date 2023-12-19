package com.example.aftas.Services.Impl;

import static org.junit.jupiter.api.Assertions.*;

import com.example.aftas.model.Dto.CompetitionDto;
import com.example.aftas.model.Dto.HuntingDto;
import com.example.aftas.model.Dto.RankingDto;
import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Entities.Fish;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.Entities.Ranking;
import com.example.aftas.model.mappers.Mapper;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.RankingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.sql.Time;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RankingServiceTest {

    @Mock
    private RankingRepository rankingRepository;

    @Mock
    private CompetitionService competitionService;

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private Mapper<Ranking, RankingDto> rankingMapper;

    @Mock
    private Mapper<Competition, CompetitionDto> competitionMapper;

    @InjectMocks
    private RankingService rankingService;

    @Test
    public void saveNewRankingSuccessfully() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        HuntingDto huntingDto = createSampleHuntingDto();
        when(competitionService.competitionValidity(anyString())).thenReturn(true);
        when(rankingRepository.findRankingByMemberNum(anyInt())).thenReturn(Optional.empty());
        when(competitionService.getCompetitionByCode(anyString())).thenReturn(createSampleCompetitionDto());
        when(rankingMapper.mapTo(any())).thenReturn(createSampleRankingDto());
        when(rankingRepository.save(any())).thenReturn(createSampleRanking());

        // Act
        RankingDto result = rankingService.save(huntingDto);

        // Assert
        assertNotNull(result);
        // Add more assertions based on the expected behavior of your method
    }

    @Test
    public void updateExistingRankingSuccessfully() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        HuntingDto huntingDto = createSampleHuntingDto();
        when(competitionService.competitionValidity(anyString())).thenReturn(true);
        when(rankingRepository.findRankingByMemberNum(anyInt())).thenReturn(Optional.of(createSampleRanking()));
        when(competitionService.getCompetitionByCode(anyString())).thenReturn(createSampleCompetitionDto());
        when(rankingMapper.mapTo(any())).thenReturn(createSampleRankingDto());
        when(rankingRepository.save(any())).thenReturn(createSampleRanking());

        // Act
        RankingDto result = rankingService.save(huntingDto);

        // Assert
        assertNotNull(result);
        // Add more assertions based on the expected behavior of your method
    }

    @Test
    public void saveRankingWithInvalidCompetition() {
        // Arrange
        MockitoAnnotations.initMocks(this);
        HuntingDto huntingDto = createSampleHuntingDto();
        when(competitionService.competitionValidity(anyString())).thenReturn(false);

        // Act
        RankingDto result = rankingService.save(huntingDto);

        // Assert
        assertNull(result);
        // Add more assertions based on the expected behavior of your method
    }

    // Add more test cases for edge cases, error scenarios, etc.

    private HuntingDto createSampleHuntingDto() {
        HuntingDto huntingDto = new HuntingDto();
        huntingDto.setId(1);
        huntingDto.setNumberOfFish(5);

        Fish fish = new Fish();
        // Set properties for the fish object
        huntingDto.setFish(fish);

        Member member = new Member();
        // Set properties for the member object
        huntingDto.setMember(member);

        Competition competition = new Competition();
        competition.setCode("COMP001");
        // Set properties for the competition object
        huntingDto.setCompetition(competition);

        return huntingDto;
    }

    private CompetitionDto createSampleCompetitionDto() {
        CompetitionDto competitionDto = new CompetitionDto();
        competitionDto.setCode("COMP001");
        competitionDto.setDate(new Date());
        competitionDto.setStartTime(Time.valueOf("12:00:00"));
        competitionDto.setEndTime(Time.valueOf("15:00:00"));
        competitionDto.setNumberOfParticipants(10);
        competitionDto.setLocation("Sample Location");
        competitionDto.setAmount(100.0);

        // You may also set the list of huntings if applicable
        // competitionDto.setHuntings(createSampleHuntings());

        return competitionDto;
    }

    private RankingDto createSampleRankingDto() {
        RankingDto rankingDto = new RankingDto();
        // Set properties for the rankingDto object

        return rankingDto;
    }

    private Ranking createSampleRanking() {
        Ranking ranking = new Ranking();
        // Set properties for the ranking object

        return ranking;
    }


}
