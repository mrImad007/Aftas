package com.example.aftas.model.mappers.Implementation;

import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Dto.CompetitionDto;
import com.example.aftas.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CompetitionMapper implements Mapper<Competition, CompetitionDto> {
    private final ModelMapper modelMapper;

    public CompetitionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CompetitionDto mapTo(Competition competition) {
        return modelMapper.map(competition,CompetitionDto.class);
    }

    @Override
    public Competition mapFrom(CompetitionDto competitionDto) {
        return modelMapper.map(competitionDto, Competition.class);
    }
}
