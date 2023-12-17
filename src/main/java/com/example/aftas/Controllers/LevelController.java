package com.example.aftas.Controllers;

import com.example.aftas.Services.Impl.LevelService;
import com.example.aftas.model.Dto.LevelDto;
import com.example.aftas.model.Dto.Requests.LevelRequest;
import com.example.aftas.model.Entities.Level;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public LevelDto getLevelByDescription(@PathVariable("description") String description){
        return levelService.findLevelByDescription(description);
    }
    @PostMapping
    public LevelDto addLevel(@RequestBody @NotNull LevelRequest levelRequest){
        return levelService.addLevel(levelRequest);
    }

    @PutMapping
    public LevelDto updateLevel(@RequestBody @NotNull LevelRequest levelRequest){
        return levelService.updateLevel(levelRequest);
    }

    @DeleteMapping("/{description}")
    public boolean deleteLevel(@PathVariable("description") @NotNull String description){
        return levelService.deleteLevel(description);
    }

}
