package com.example.sb.model.mappers.Impl;

import com.example.sb.model.Entities.Rayon;
import com.example.sb.model.Entities.Responsable;
import com.example.sb.model.dto.RayonDto;
import com.example.sb.model.dto.ResponsableDto;
import com.example.sb.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component

public class ResponsableMapper implements Mapper<Responsable, ResponsableDto> {

    private final ModelMapper modelMapper;
    public ResponsableMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponsableDto mapTo(final Responsable responsable) {
        return modelMapper.map(responsable, ResponsableDto.class);
    }

    @Override
    public Responsable mapFrom(final ResponsableDto responsableDto) {
        return modelMapper.map(responsableDto, Responsable.class);
    }
}
