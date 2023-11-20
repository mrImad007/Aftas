package com.example.sb.model.mappers.Impl;

import com.example.sb.model.Entities.Admin;
import com.example.sb.model.dto.AdminDto;
import com.example.sb.model.mappers.Mapper;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class AdminMapper implements Mapper<Admin, AdminDto> {

    private final ModelMapper modelMapper;


    public AdminMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AdminDto mapTo(Admin adminEntity) {
        return modelMapper.map(adminEntity, AdminDto.class);
    }

    @Override
    public Admin mapFrom(AdminDto adminDto) {
        return modelMapper.map(adminDto, Admin.class);
    }
}
