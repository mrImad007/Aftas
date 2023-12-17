package com.example.aftas.Services.Impl;

import com.example.aftas.Dao.RankingDao;
import com.example.aftas.model.Dto.Requests.RankingRequest;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.Entities.Ranking;
import com.example.aftas.model.Dto.RankingDto;
import com.example.aftas.model.Entities.RankingId;
import com.example.aftas.model.mappers.Mapper;
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
    private final Mapper<Ranking, RankingDto> rankingMapper;

    @Autowired
    public RankingService(RankingRepository rankingRepository, CompetitionService competitionService, Mapper<Ranking, RankingDto> rankingMapper) {
        this.competitionService = competitionService;
        this.rankingMapper = rankingMapper;
        this.rankingRepository = rankingRepository;
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

        if (!rankingOptional.isPresent()) {
            if (competitionService.competitionValidity(rankingRequest.getCompetition_code())) {
                RankingId rankingId = new RankingId(rankingRequest.getCompetition_code(), rankingRequest.getMember_num());
                Ranking ranking = rankingRequest.toModel();
                ranking.setRankingId(rankingId);
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
}
