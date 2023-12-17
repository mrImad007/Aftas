package com.example.aftas.model.Dto.Requests;

import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.Entities.Ranking;
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
    @NotEmpty(message = "Member number must be provided")
    private Integer member_num;
    @NotBlank(message = "Competition code must be provided")
    private String competition_code;
    @NotEmpty(message = "Rank must be provided")
    private Integer rank;
    @NotEmpty(message = "Score must be provided")
    private Integer score;

    public Ranking toModel(){
        Member member = Member.builder().num(this.member_num).build();
        Competition competition = Competition.builder().code(this.competition_code).build();

        return Ranking
                .builder()
                .member(member)
                .competition(competition)
                .rank(this.rank)
                .score(this.score)
                .build();
    }
}
