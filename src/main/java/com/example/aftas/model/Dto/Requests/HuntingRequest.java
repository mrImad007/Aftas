package com.example.aftas.model.Dto.Requests;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HuntingRequest {
    @NotEmpty(message = "id must be provided")
    private Integer id;
    @NotEmpty(message = "fish number must be provided")
    private Integer numberOfFish;
    @NotEmpty(message = "fish id must be provided")
    private Integer fish_id;
    @NotEmpty(message = "member id must be provided")
    private Integer member_id;
    @NotEmpty(message = "competition id must be provided")
    private Integer competition_id;
}
