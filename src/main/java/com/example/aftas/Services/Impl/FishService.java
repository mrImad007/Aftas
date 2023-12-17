package com.example.aftas.Services.Impl;

import com.example.aftas.model.Dto.FishDto;
import com.example.aftas.model.Dto.Requests.FishRequest;
import com.example.aftas.model.Entities.Fish;
import com.example.aftas.model.mappers.Mapper;
import com.example.aftas.repository.FishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FishService {
    private final FishRepository fishRepository;

    private final Mapper<Fish, FishDto> fishMapper;

    @Autowired
    public FishService(FishRepository fishRepository, Mapper<Fish, FishDto> fishMapper) {
        this.fishRepository = fishRepository;
        this.fishMapper = fishMapper;
    }
    public List<FishDto> getAllFishes() {
        return fishRepository.findAll().stream().map(fishMapper::mapTo).collect(Collectors.toList());
    }

    public FishDto findFishByName(String name){
        Optional<Fish> fishOptional = fishRepository.findByName(name);
        if (fishOptional.isPresent()){
            Fish fish = fishOptional.get();
            return fishMapper.mapTo(fish);
        }else {
            return null;
        }
    }

    public FishDto updateFish(FishRequest fishRequest){
        Fish fish = fishRepository.save(fishRequest.toModel());
        return fishMapper.mapTo(fish);
    }

    public void deleteFish(String name){
        fishRepository.deleteById(name);
    }

    public boolean checkAverageWeight(String name, Double weight){
        Optional<Fish> optionalFish = fishRepository.findByName(name);
        if (optionalFish.isPresent()){
            Fish fish = optionalFish.get();
            if (weight >= fish.getAverageWeight()){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }


}
