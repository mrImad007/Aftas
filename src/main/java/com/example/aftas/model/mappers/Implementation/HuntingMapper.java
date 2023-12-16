package com.example.aftas.model.mappers.Implementation;

import com.example.aftas.model.Dto.HuntingDto;
import com.example.aftas.model.Entities.Hunting;
import com.example.aftas.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HuntingMapper implements Mapper<Hunting, HuntingDto> {
    private final ModelMapper modelMapper;
    public HuntingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public HuntingDto mapTo(Hunting hunting) {
        return modelMapper.map(hunting, HuntingDto.class);
    }
    @Override
    public Hunting mapFrom(HuntingDto huntingDto) {
        return modelMapper.map(huntingDto, Hunting.class);
    }
}
