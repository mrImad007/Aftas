package com.example.aftas.model.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NonNull
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String name;
    private float averageWeight;
    @OneToMany(mappedBy = "fish")
    private List<Hunting> huntings;
    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

}
