package com.example.aftas.model.mappers.Implementation;

import com.example.aftas.model.Entities.Ranking;
import com.example.aftas.model.dto.RankingDto;
import com.example.aftas.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RankingMapper implements Mapper<Ranking, RankingDto> {
    private final ModelMapper modelMapper;

    public RankingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RankingDto mapTo(Ranking ranking) {
        return modelMapper.map(ranking, RankingDto.class);
    }

    @Override
    public Ranking mapFrom(RankingDto rankingDto) {
        return modelMapper.map(rankingDto, Ranking.class);
    }
}
