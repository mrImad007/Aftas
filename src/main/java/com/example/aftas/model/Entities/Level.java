package com.example.aftas.model.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NonNull
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer code;
    private String description;
    private Integer points;
    @OneToMany(mappedBy = "level")
    private List<Fish> fishes;
}
