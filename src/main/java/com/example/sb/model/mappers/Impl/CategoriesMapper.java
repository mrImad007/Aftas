package com.example.sb.model.mappers.Impl;

import com.example.sb.model.Entities.Admin;
import com.example.sb.model.Entities.Categories;
import com.example.sb.model.dto.AdminDto;
import com.example.sb.model.dto.CategoriesDto;
import com.example.sb.model.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class CategoriesMapper  implements Mapper<Categories, CategoriesDto> {

    private final ModelMapper modelMapper;

    public CategoriesMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoriesDto mapTo(Categories categoriesDto) {
        return modelMapper.map(categoriesDto, CategoriesDto.class);
    }

    @Override
    public Categories mapFrom(CategoriesDto categoriesDto) {
        return modelMapper.map(categoriesDto, Categories.class);    }
}
