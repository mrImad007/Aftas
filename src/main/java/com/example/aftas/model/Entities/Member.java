package com.example.aftas.model.Entities;

import com.example.aftas.model.Enum.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NonNull
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;
    private String name;
    private String familyName;
    private Date accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    private String identityNumber;
    @OneToMany(mappedBy = "member")
    private List<Ranking> rankings;
    @OneToMany(mappedBy = "member")
    private List<Hunting> huntings;

}
