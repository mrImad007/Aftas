package com.example.aftas.Services.Impl;

import com.example.aftas.Dao.LevelDao;
import com.example.aftas.model.Dto.LevelDto;
import com.example.aftas.model.Dto.Requests.LevelRequest;
import com.example.aftas.model.Entities.Level;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.mappers.Mapper;
import com.example.aftas.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LevelService implements LevelDao {
    private final LevelRepository levelRepository;
    private final Mapper<Level, LevelDto> levelMapper;

    @Autowired
    public LevelService(LevelRepository levelRepository, Mapper<Level, LevelDto> levelMapper) {
        this.levelRepository = levelRepository;
        this.levelMapper = levelMapper;
    }

    @Override
    public List<LevelDto> getAllLevels() {
        return levelRepository.findAll().stream().map(levelMapper::mapTo).collect(Collectors.toList());
    }

    public LevelDto findLevelByDescription(String descritpion){
        Optional<Level> levelOptional = levelRepository.findLevelByDescription(descritpion);
        if(levelOptional.isPresent()){
            return levelMapper.mapTo(levelOptional.get());
        }else{
            return null;
        }
    }

    public LevelDto addLevel(LevelRequest levelRequest){
        Optional<Level> levelOptional = levelRepository.findLevelByDescription(levelRequest.getDescription());
        if (levelOptional.isPresent()){
            return levelMapper.mapTo(levelRepository.save(levelRequest.toModel()));
        }else {
            return updateLevel(levelRequest);
        }
    }

    public LevelDto updateLevel(LevelRequest levelRequest){
        return levelMapper.mapTo(levelRepository.save(levelRequest.toModel()));
    }

    public boolean deleteLevel(String description){
        Optional<Level> levelOptional = levelRepository.findLevelByDescription(description);
        if (levelOptional.isPresent()){
            levelRepository.delete(levelOptional.get());
            return true;
        }else {
            return false;
        }
    }
}
