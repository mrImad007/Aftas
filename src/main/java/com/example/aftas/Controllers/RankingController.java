package com.example.aftas.Controllers;

import com.example.aftas.Services.Impl.RankingService;
import com.example.aftas.model.Dto.RankingDto;
import com.example.aftas.model.Entities.Ranking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/rankings")
public class RankingController {
    private final RankingService rankingService;

    public RankingController(RankingService rankingService){
        this.rankingService = rankingService;
    }

    @GetMapping
    public List<RankingDto> getAllRankings(){
        return rankingService.getAllRankings();
    }
    @GetMapping("{code}")
    public List<Ranking> findCompetionRanking(@PathVariable("code") String code){
        return rankingService.findRankingbyCompetitionCode(code);
    }
}
