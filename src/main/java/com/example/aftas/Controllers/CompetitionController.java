package com.example.aftas.Controllers;

import com.example.aftas.model.dto.CompetitionDto;
import com.example.aftas.Services.Impl.CompetitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/competitions")
public class CompetitionController {
    public final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }
    @GetMapping
    public List<CompetitionDto> getAllCompetitions(){
        return competitionService.getAllCompetitions();
    }
    @GetMapping("/{code}")
    public ResponseEntity<Object> findCompetitionByCode(@PathVariable("code") String code){
        return competitionService.getCompetitionByCode(code);
    }
}
