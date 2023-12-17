package com.example.aftas.model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ranking {
    @EmbeddedId
    private RankingId rankingId;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("member_num")
    @JsonIgnore
    private Member member;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("competition_code")
    @JsonIgnore
    private Competition competition;
    private Integer rank;
    private Integer score;

}
