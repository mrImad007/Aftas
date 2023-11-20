package com.example.sb.model.mappers.Impl;

import com.example.sb.model.Entities.Rayon;
import com.example.sb.model.dto.RayonDto;
import com.example.sb.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class RayonMapper implements Mapper<Rayon, RayonDto> {

    private final ModelMapper modelMapper;

    public RayonMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RayonDto mapTo(Rayon rayon) {
        return modelMapper.map(rayon, RayonDto.class);
    }

    @Override
    public Rayon mapFrom(RayonDto rayonDto) {
        return modelMapper.map(rayonDto, Rayon.class);
    }
}
