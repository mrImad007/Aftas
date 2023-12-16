package com.example.aftas.model.mappers.Implementation;

import com.example.aftas.model.Dto.FishDto;
import com.example.aftas.model.Entities.Fish;
import com.example.aftas.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FishMapper implements Mapper<Fish, FishDto> {
    private final ModelMapper modelMapper;

    public FishMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FishDto mapTo(Fish fish) {
        return modelMapper.map(fish, FishDto.class);
    }

    @Override
    public Fish mapFrom(FishDto fishDto) {
        return modelMapper.map(fishDto, Fish.class);
    }
}
