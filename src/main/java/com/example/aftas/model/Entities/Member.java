package com.example.aftas.model.Entities;

import com.example.aftas.model.Enum.IdentityDocumentType;
import com.fasterxml.jackson.annotation.*;
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
    @Column(name = "num")
    private Integer num;
    private String name;
    private String familyName;
    private Date accessionDate;
    @Column(nullable = false)
    private String nationality;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdentityDocumentType identityDocumentType;
    @Column(unique = true, nullable = false)
    private String identityNumber;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Hunting> huntings;

    @ManyToMany
    @JsonIgnore
    private List<Competition> competitions;
}
