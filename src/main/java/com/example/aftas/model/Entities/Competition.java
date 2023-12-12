package com.example.aftas.model.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.context.annotation.Primary;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NonNull
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @NotEmpty
    private String code;
    @NotEmpty
    @FutureOrPresent
    private Date date;
    @NotEmpty
    private Time startTime;
    @NotEmpty
    private Time endTime;
    @NotEmpty
    private Integer numberOfParticipants;
    @NotEmpty
    private String location;
    @NotEmpty
    private Double amount;
    @OneToMany(mappedBy = "competition")
    private List<Ranking> rankings;
    @OneToMany(mappedBy = "competition")
    private List<Hunting> huntings;
}
