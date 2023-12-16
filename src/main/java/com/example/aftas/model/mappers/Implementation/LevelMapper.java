package com.example.aftas.model.mappers.Implementation;

import com.example.aftas.model.Dto.LevelDto;
import com.example.aftas.model.Entities.Level;
import com.example.aftas.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LevelMapper implements Mapper<Level, LevelDto> {
    private final ModelMapper modelMapper;

    public LevelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LevelDto mapTo(Level level) {
        return modelMapper.map(level, LevelDto.class);
    }

    @Override
    public Level mapFrom(LevelDto levelDto) {
        return modelMapper.map(levelDto, Level.class);
    }
}
