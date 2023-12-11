package com.example.aftas.model.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Time;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NonNull
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;
    private Time startTime;
    private Time endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;
    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;
    @OneToMany(mappedBy = "competition")
    private List<Hunting> huntings;
}
