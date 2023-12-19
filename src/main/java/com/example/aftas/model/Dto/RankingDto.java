package com.example.aftas.model.Dto;

import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Entities.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankingDto {
    @NotEmpty(message = "Member must be provided")
    private MemberDto member;
    @NotEmpty(message = "Competition must be provided")
    private CompetitionDto competition;
    @NotEmpty(message = "Rank must be provided")
    private Integer rank;
    @NotEmpty(message = "Score must be provided")
    private Integer score;
}
