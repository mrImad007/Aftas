package com.example.sb.model.mappers.Impl;

import com.example.sb.model.Entities.SupAdmin;
import com.example.sb.model.dto.SupAdminDto;
import com.example.sb.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class SuperAdminMapper implements Mapper<SupAdmin, SupAdminDto> {

    private final ModelMapper modelMapper;

    public SuperAdminMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SupAdminDto mapTo(SupAdmin superAdmin) {
        return modelMapper.map(superAdmin, SupAdminDto.class);
    }

    @Override
    public SupAdmin mapFrom(SupAdminDto superAdminDto) {
        return modelMapper.map(superAdminDto, SupAdmin.class);
    }
}
