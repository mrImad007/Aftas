package com.example.aftas.model.dto;

import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Entities.Hunting;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.Entities.Ranking;
import com.example.aftas.model.Enum.IdentityDocumentType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequest {
    @NotEmpty(message = "Nationality must be provided")
    private Integer num;
    @NotBlank(message = "Name name must be provided")
    private String name;
    @NotEmpty(message = "Family name must be provided")
    private String familyName;
    @PastOrPresent(message = "Accession date must today's")
    @NotEmpty(message = "Accession date must be provided")
    private Date accessionDate;
    @NotEmpty(message = "Nationality must be provided")
    private String nationality;
    @NotEmpty(message = "Nationality document type must be provided")
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    @NotEmpty(message = "Nationality number must be provided")
    @Column(unique = true)
    private String identityNumber;
    private Integer ranking_id;
    private Integer hunting_id;



    /*public Member toModel(){
        List<Ranking> rankingList = new ArrayList<>();
        List<Hunting> huntingList = new ArrayList<>();
        if (ranking_id != null) {
            Ranking ranking = Ranking.builder().id(ranking_id).build();
            rankingList.add(ranking);
        }

        if (hunting_id != null) {
            Hunting hunting = Hunting.builder().id(hunting_id).build();
            huntingList.add(hunting);
        }
        return Member
                .builder()
                .num(num)
                .name(name)
                .familyName(familyName)
                .accessionDate(accessionDate)
                .nationality(nationality)
                .identityDocumentType(identityDocumentType)
                .identityNumber(identityNumber)
                .rankings(rankingList)
                .huntings(huntingList)
                .build();
    }*/

}
