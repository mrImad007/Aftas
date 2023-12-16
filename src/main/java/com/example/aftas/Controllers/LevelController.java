package com.example.aftas.Controllers;

import com.example.aftas.Services.Impl.LevelService;
import com.example.aftas.model.Dto.LevelDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/levels")
public class LevelController {
    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping
    public List<LevelDto> getAllLevels(){
        return levelService.getAllLevels();
    }
    @GetMapping("/description/{description}")
    public ResponseEntity<Object> getLevelByDescription(@PathVariable("description") String description){
        return levelService.findLevelByDescription(description);
    }

}
