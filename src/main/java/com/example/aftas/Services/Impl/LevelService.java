package com.example.aftas.Services.Impl;

import com.example.aftas.Dao.LevelDao;
import com.example.aftas.model.Dto.LevelDto;
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

    public ResponseEntity<Object> findLevelByDescription(String descritpion){
        Optional<Level> levelOptional = levelRepository.findLevelByDescription(descritpion);
        if(levelOptional.isPresent()){
            Level level = levelOptional.get();
            LevelDto levelDto = levelMapper.mapTo(level);
            return ResponseEntity.ok(levelDto);
        }else{
            String error = "No level found with this description: "+ descritpion;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
