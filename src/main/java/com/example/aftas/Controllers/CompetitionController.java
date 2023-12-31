package com.example.aftas.Controllers;

import com.example.aftas.model.Dto.CompetitionDto;
import com.example.aftas.Services.Impl.CompetitionService;
import com.example.aftas.model.Dto.Requests.CompetitionRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/competitions")
@CrossOrigin("http://localhost:4200")
public class CompetitionController {
    public final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }
    @GetMapping
    public List<CompetitionDto> getAllCompetitions(){
        return competitionService.getAllCompetitions();
    }

    @GetMapping("/pagination")
    public Page<CompetitionDto> findAll(Pageable pageable){
        return competitionService.findAllPaginated(pageable);
    }
    @GetMapping("/{code}")
    public CompetitionDto findCompetitionByCode(@PathVariable("code") @NotNull String code){
        return competitionService.getCompetitionByCode(code);
    }
    @PostMapping
    public CompetitionDto save(@RequestBody @NotNull CompetitionRequest competitionRequest) {
        return competitionService.save(competitionRequest);
    }
    @DeleteMapping("/{code}")
    public boolean deleteCompetition(@PathVariable("code") @NotNull String code){
        if(competitionService.getCompetitionByCode(code) != null){
            competitionService.deleteCompetition(code);
            return true;
        }else{
            return false;
        }
    }
}
