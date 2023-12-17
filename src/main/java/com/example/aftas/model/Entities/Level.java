package com.example.aftas.model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;
    @Column(unique = true, name = "description")
    private String description;
    private Integer points;
    @OneToMany(mappedBy = "level")
    @JsonIgnore
    private List<Fish> fishes;

    @Override
    public String toString() {
        return "Level{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", points=" + points +
                '}';
    }
}
