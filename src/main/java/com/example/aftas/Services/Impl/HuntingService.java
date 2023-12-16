package com.example.aftas.Services.Impl;

import com.example.aftas.model.Dto.HuntingDto;
import com.example.aftas.model.Entities.Hunting;
import com.example.aftas.model.mappers.Mapper;
import com.example.aftas.repository.HuntingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HuntingService {
    private final HuntingRepository huntingRepository;

    private final Mapper<Hunting, HuntingDto> huntingMapper;
    @Autowired
    public HuntingService(HuntingRepository huntingRepository, Mapper<Hunting, HuntingDto> huntingMapper) {
        this.huntingRepository = huntingRepository;
        this.huntingMapper = huntingMapper;
    }

    public List<HuntingDto> getAllHuntings(){
        return huntingRepository.findAll().stream().map(huntingMapper::mapTo).collect(Collectors.toList());
    }

    public List<HuntingDto> findHuntingByCompetitionCode(String code){
        return huntingRepository.findHuntingByCompetitionCode(code).stream().map(huntingMapper::mapTo).collect(Collectors.toList());
    }
}
