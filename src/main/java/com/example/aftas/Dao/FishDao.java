package com.example.aftas.Dao;

import com.example.aftas.model.Dto.FishDto;
import com.example.aftas.model.Entities.Fish;

import java.util.List;

public interface FishDao{
    List<FishDto> getAllFishes();
}
