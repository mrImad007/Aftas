package com.example.aftas.model.Dto;

import com.example.aftas.model.Entities.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FishDto {
    @NotBlank(message = "name must be provided")
    private String name;
    @NotEmpty(message = "weight must be provided")
    private float averageWeight;
    @NotEmpty(message = "level must be provided")
    private Level level;
}
