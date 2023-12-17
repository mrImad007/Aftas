package com.example.aftas.model.Dto.Requests;


import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Entities.Fish;
import com.example.aftas.model.Entities.Hunting;
import com.example.aftas.model.Entities.Member;
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
    private String fish_id;
    @NotEmpty(message = "member id must be provided")
    private Integer member_id;
    @NotEmpty(message = "competition id must be provided")
    private String competition_id;

    public Hunting toModel(){
        Fish fish = Fish.builder().name(this.fish_id).build();
        Member member = Member.builder().num(this.member_id).build();
        Competition competition = Competition.builder().code(this.competition_id).build();

        return Hunting
                .builder()
                .id(this.id)
                .numberOfFish(this.numberOfFish)
                .fish(fish)
                .competition(competition)
                .member(member)
                .build();
    }
}
