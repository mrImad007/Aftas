package com.example.aftas.model.dto;

import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Entities.Hunting;
import com.example.aftas.model.Entities.Ranking;
import com.example.aftas.model.Enum.IdentityDocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    @NotEmpty(message = "Id must be provided")
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
    private List<Ranking> rankings;
    private List<Hunting> huntings;
}
