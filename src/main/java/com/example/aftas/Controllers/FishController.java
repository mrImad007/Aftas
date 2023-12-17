package com.example.aftas.Controllers;

import com.example.aftas.Services.Impl.FishService;
import com.example.aftas.model.Dto.FishDto;
import com.example.aftas.model.Dto.Requests.FishRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fishes")
public class FishController {
    private final FishService fishService;

    @Autowired
    public FishController(FishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping
    public List<FishDto> getAll(){
        return fishService.getAllFishes();
    }

    @PostMapping
    public FishDto addFish(@RequestBody @NotNull FishRequest fishRequest){
        if (fishService.findFishByName(fishRequest.getName()) == null){
            return fishService.updateFish(fishRequest);
        }else {
            return null;
        }
    }

    @PutMapping
    public FishDto updateFish(@RequestBody @NotNull FishRequest fishRequest){
        return fishService.updateFish(fishRequest);
    }

    @DeleteMapping("/{name}")
    public boolean deleteFish(@PathVariable("name") @NotNull String name){
        if (fishService.findFishByName(name) != null){
            fishService.deleteFish(name);
            return true;
        }else {
            return false;
        }
    }
}
