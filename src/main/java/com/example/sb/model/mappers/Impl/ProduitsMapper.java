package com.example.sb.model.mappers.Impl;

import com.example.sb.model.Entities.Produits;
import com.example.sb.model.dto.ProduitsDto;
import com.example.sb.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class ProduitsMapper implements Mapper<Produits, ProduitsDto> {

    private final ModelMapper modelMapper;

    public ProduitsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProduitsDto mapTo(Produits produits) {
        return modelMapper.map(produits, ProduitsDto.class);
    }

    @Override
    public Produits mapFrom(ProduitsDto produitsDto) {
        return modelMapper.map(produitsDto, Produits.class);
    }
}
