package com.example.aftas.Services.Impl;

import com.example.aftas.model.Dto.HuntingDto;
import com.example.aftas.model.Dto.RankingDto;
import com.example.aftas.model.Dto.Requests.HuntingRequest;
import com.example.aftas.model.Entities.*;
import com.example.aftas.model.mappers.Mapper;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.repository.HuntingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HuntingService {
    private final HuntingRepository huntingRepository;
    private final FishRepository fishRepository;
    private final RankingService rankingService;
    private final Mapper<Hunting, HuntingDto> huntingMapper;
    @Autowired
    public HuntingService(HuntingRepository huntingRepository, FishRepository fishRepository, RankingService rankingService, Mapper<Hunting, HuntingDto> huntingMapper) {
        this.huntingRepository = huntingRepository;
        this.fishRepository = fishRepository;
        this.rankingService = rankingService;
        this.huntingMapper = huntingMapper;
    }

    public List<HuntingDto> getAllHuntings(){
        return huntingRepository.findAll().stream().map(huntingMapper::mapTo).collect(Collectors.toList());
    }

    public List<HuntingDto> findHuntingByCompetitionCode(String code){
        return huntingRepository.findHuntingByCompetitionCode(code).stream().map(huntingMapper::mapTo).collect(Collectors.toList());
    }

    public HuntingDto addHunting(HuntingRequest huntingRequest) {
        if (rankingService.checkBeforeHunting(huntingRequest.getMember_id(),huntingRequest.getCompetition_id())){
            List<Hunting> existingHunting = huntingRepository.findByMemberNumAndCompetitionCode(
                    huntingRequest.getMember_id(), huntingRequest.getCompetition_id());

            if (!existingHunting.isEmpty()) {
                for (Hunting hunting : existingHunting) {
                    String fishName = hunting.getFish().getName();
                    if (fishName.equals(huntingRequest.getFish_id())) {

                        int oldNumberOfFish = hunting.getNumberOfFish();
                        int newNumberOfFish = huntingRequest.getNumberOfFish();
                        huntingRequest.setNumberOfFish(oldNumberOfFish + newNumberOfFish);

                        hunting.setNumberOfFish(huntingRequest.getNumberOfFish());

                        System.out.println("Updated existing hunting record.");
                        HuntingDto huntingDto = huntingMapper.mapTo(huntingRepository.save(hunting));
                        rankingService.save(huntingDto);
                        return huntingDto;
                    }
                }
            }

            Fish newFish = Fish.builder().name(huntingRequest.getFish_id()).build();
            Member member = Member.builder().num(huntingRequest.getMember_id()).build();
            Competition competition = Competition.builder().code(huntingRequest.getCompetition_id()).build();

            Hunting newHunting = Hunting.builder()
                    .numberOfFish(huntingRequest.getNumberOfFish())
                    .fish(newFish)
                    .member(member)
                    .competition(competition)
                    .build();

            System.out.println("Created new hunting record.");
            HuntingDto huntingDto = huntingMapper.mapTo(huntingRepository.save(newHunting));
            rankingService.save(huntingDto);
            return huntingDto;
        }else {
            return null;
        }
    }




}
