package com.example.aftas.model.Dto.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingRequest {
    @NotEmpty(message = "Identifier must be provided")
    private Integer id;
    @NotEmpty(message = "Member number must be provided")
    private Integer member_num;
    @NotBlank(message = "Competition code must be provided")
    private String competition_code;
    @NotEmpty(message = "Rank must be provided")
    private Integer rank;
    @NotEmpty(message = "Score must be provided")
    private Integer score;
}
