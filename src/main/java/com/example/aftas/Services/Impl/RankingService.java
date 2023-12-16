package com.example.aftas.Services.Impl;

import com.example.aftas.Dao.RankingDao;
import com.example.aftas.model.Entities.Ranking;
import com.example.aftas.model.Dto.RankingDto;
import com.example.aftas.model.mappers.Mapper;
import com.example.aftas.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingService implements RankingDao {

    private final RankingRepository rankingRepository;
    private final Mapper<Ranking, RankingDto> rankingMapper;

    @Autowired
    public RankingService(RankingRepository rankingRepository, Mapper<Ranking, RankingDto> rankingMapper) {
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
}
