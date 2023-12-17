package com.example.aftas.Controllers;

import com.example.aftas.Services.Impl.CompetitionService;
import com.example.aftas.Services.Impl.RankingService;
import com.example.aftas.model.Dto.RankingDto;
import com.example.aftas.model.Dto.Requests.RankingRequest;
import com.example.aftas.model.Entities.Ranking;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rankings")
public class RankingController {
    private final RankingService rankingService;
    private final CompetitionService competitionService;


    public RankingController(RankingService rankingService, CompetitionService competitionService){
        this.rankingService = rankingService;
        this.competitionService = competitionService;
    }

    @GetMapping
    public List<RankingDto> getAllRankings(){
        return rankingService.getAllRankings();
    }
    @GetMapping("{code}")
    public List<Ranking> findCompetionRanking(@PathVariable("code") @NotNull String code){
        return rankingService.findRankingbyCompetitionCode(code);
    }
    @PostMapping
    public RankingDto addRanking(@RequestBody @NotNull RankingRequest rankingRequest){
        return rankingService.addRanking(rankingRequest);
    }

    @DeleteMapping("/{identityNum}")
    public boolean deleteRanking(@PathVariable("identityNum") @NotNull Integer identityNum){
        return rankingService.deleteRanking(identityNum);
    }
}
