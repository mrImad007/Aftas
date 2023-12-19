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
@CrossOrigin("http://localhost:4200")
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
    @GetMapping("/{name}")
    public FishDto findFishByName(@PathVariable("name") @NotNull String name){
        return fishService.findFishByName(name);
    }

    @PostMapping("/checkWeight")
    public boolean checkFishWeight(@RequestBody FishRequest fishRequest){
        return fishService.checkAverageWeight(fishRequest);
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
