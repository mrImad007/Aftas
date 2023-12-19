package com.example.aftas.Services.Impl;

import com.example.aftas.Dao.RankingDao;
import com.example.aftas.model.Dto.CompetitionDto;
import com.example.aftas.model.Dto.HuntingDto;
import com.example.aftas.model.Dto.Requests.RankingRequest;
import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Entities.Ranking;
import com.example.aftas.model.Dto.RankingDto;
import com.example.aftas.model.Entities.RankingId;
import com.example.aftas.model.mappers.Mapper;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RankingService implements RankingDao {

    private final RankingRepository rankingRepository;
    private final CompetitionService competitionService;
    private final CompetitionRepository competitionRepository;
    private final FishService fishService;
    private final Mapper<Ranking, RankingDto> rankingMapper;
    private final Mapper<Competition, CompetitionDto> competitionMapper;

    @Autowired
    public RankingService(RankingRepository rankingRepository, CompetitionService competitionService, CompetitionRepository competitionRepository, FishService fishService, Mapper<Ranking, RankingDto> rankingMapper, Mapper<Competition, CompetitionDto> competitionMapper) {
        this.competitionService = competitionService;
        this.competitionRepository = competitionRepository;
        this.fishService = fishService;
        this.rankingMapper = rankingMapper;
        this.rankingRepository = rankingRepository;
        this.competitionMapper = competitionMapper;
    }

    @Override
    public List<RankingDto> getAllRankings() {
        return rankingRepository.findAll().stream().map(rankingMapper::mapTo).collect(Collectors.toList());
    }

    public List<Ranking> findRankingbyCompetitionCode(String code){
        return rankingRepository.findRankingByCompetitionCode(code);
    }
    public Optional<Ranking> findRankingByMemberNum(Integer num){
        return rankingRepository.findRankingByMemberNum(num);
    }

    public RankingDto addRanking(RankingRequest rankingRequest) {
        Optional<Ranking> rankingOptional = findRankingByMemberNum(rankingRequest.getMember_num());

        CompetitionDto competitionDto = competitionService.getCompetitionByCode(rankingRequest.getCompetition_code());
        Integer oldParticipantsNumber = competitionDto.getNumberOfParticipants();
        competitionDto.setNumberOfParticipants(oldParticipantsNumber+1);


        if (!rankingOptional.isPresent()) {
            if (competitionService.competitionValidity(rankingRequest.getCompetition_code())) {
                RankingId rankingId = new RankingId(rankingRequest.getCompetition_code(), rankingRequest.getMember_num());
                Ranking ranking = rankingRequest.toModel();
                ranking.setRankingId(rankingId);
                ranking.setRank(0);
                ranking.setScore(0);

                competitionRepository.save(competitionMapper.mapFrom(competitionDto));
                return rankingMapper.mapTo(rankingRepository.save(ranking));
            } else {
                return null;
            }
        } else {
            Ranking actualRanking = rankingOptional.get();

            if (competitionService.competitionValidity(rankingRequest.getCompetition_code())) {
                Integer actualScore = actualRanking.getScore();
                Integer newScore = rankingRequest.getScore() + actualScore;

                actualRanking.setScore(newScore);

                competitionRepository.save(competitionMapper.mapFrom(competitionDto));
                return rankingMapper.mapTo(rankingRepository.save(actualRanking));
            } else {
                return null;
            }
        }
    }

    public RankingDto save(HuntingDto huntingDto){
        Optional<Ranking> rankingOptional = findRankingByMemberNum(huntingDto.getMember().getNum());

        if (!rankingOptional.isPresent()) {
            if (competitionService.competitionValidity(huntingDto.getCompetition().getCode())) {
                RankingId rankingId = new RankingId(huntingDto.getCompetition().getCode(), huntingDto.getMember().getNum());

                Integer fishPoints = huntingDto.getFish().getLevel().getPoints();
                Integer fishNumber = huntingDto.getNumberOfFish();
                Integer newScore = fishNumber*fishPoints;
                System.out.println("--------------Score--------- : " + newScore);

                RankingRequest rankingRequest = new RankingRequest();
                rankingRequest.setMember_num(huntingDto.getMember().getNum());
                rankingRequest.setCompetition_code(huntingDto.getCompetition().getCode());
                rankingRequest.setScore(newScore);

                Ranking ranking = rankingRequest.toModel();
                ranking.setRankingId(rankingId);

                return rankingMapper.mapTo(rankingRepository.save(ranking));
            } else {
                return null;
            }
        } else {
            Integer fishPoints = huntingDto.getFish().getLevel().getPoints();
            Integer fishNumber = huntingDto.getNumberOfFish();
            Integer Score = fishNumber*fishPoints;

            RankingRequest rankingRequest = new RankingRequest();
            rankingRequest.setMember_num(huntingDto.getMember().getNum());
            rankingRequest.setCompetition_code(huntingDto.getCompetition().getCode());
            rankingRequest.setScore(Score);

            Ranking actualRanking = rankingOptional.get();

            if (competitionService.competitionValidity(rankingRequest.getCompetition_code())) {
                Integer actualScore = actualRanking.getScore();
                Integer newScore = rankingRequest.getScore() + actualScore;

                actualRanking.setScore(newScore);

                return rankingMapper.mapTo(rankingRepository.save(actualRanking));
            } else {
                return null;
            }
        }
    }
    public boolean deleteRanking(Integer identityNumber){
        Optional<Ranking> rankingOptional = findRankingByMemberNum(identityNumber);
        if (rankingOptional.isPresent()){
            Ranking ranking = rankingOptional.get();
            rankingRepository.delete(ranking);
            return true;
        }else {
            return false;
        }
    }

    public List<RankingDto> getResult(String code){
        return rankingRepository.findTop3ByCompetitionCodeOrderByScoreDesc(code).stream().map(rankingMapper::mapTo).collect(Collectors.toList());
    }

    public boolean checkBeforeHunting(Integer num, String code){
        List<Ranking> rankingList = rankingRepository.findByMemberNumAndCompetitionCode(num,code);
        if (rankingList.size()>0){
            return true;
        }else {
            return false;
        }
    }
}


