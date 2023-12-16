package com.example.aftas.model.Dto;

import com.example.aftas.model.Entities.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HuntingDto {
    @NotEmpty(message = "id must be provided")
    private Integer id;
    @NotEmpty(message = "fish number must be provided")
    private Integer numberOfFish;
    @NotEmpty(message = "fish object must be provided")
    private Fish fish;
    @NotEmpty(message = "member object must be provided")
    private Member member;
    @NotEmpty(message = "competition object must be provided")
    private Competition competition;
}
