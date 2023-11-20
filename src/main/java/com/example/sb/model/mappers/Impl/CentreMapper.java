package com.example.sb.model.mappers.Impl;

import com.example.sb.model.Entities.Centre;
import com.example.sb.model.dto.CentreDto;
import com.example.sb.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class CentreMapper implements Mapper<Centre, CentreDto> {

    private final ModelMapper modelMapper;

    public CentreMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CentreDto mapTo(Centre centre) {
        return modelMapper.map(centre, CentreDto.class);
    }

    @Override
    public Centre mapFrom(CentreDto centreDto) {
        return modelMapper.map(centreDto, Centre.class);
    }
}
