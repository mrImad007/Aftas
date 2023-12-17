package com.example.aftas.model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NonNull
public class Hunting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private Integer numberOfFish;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Fish fish;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Member member;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Competition competition;
}
