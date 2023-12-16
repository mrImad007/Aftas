package com.example.aftas.Controllers;

import com.example.aftas.Services.Impl.HuntingService;
import com.example.aftas.model.Dto.HuntingDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/huntings")
public class HuntingController {
    private final HuntingService huntingService;

    public HuntingController(HuntingService huntingService) {
        this.huntingService = huntingService;
    }

    @GetMapping
    public List<HuntingDto> getAllHuntings(){
        return huntingService.getAllHuntings();
    }

    @GetMapping("/{code}")
    public List<HuntingDto> getHuntingByCompetitionCode(@PathVariable("code") String code){
        return huntingService.findHuntingByCompetitionCode(code);
    }

}
