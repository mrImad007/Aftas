package com.example.aftas.model.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NonNull
public class Fish {
    @Id
    @Column(name = "name")
    private String name;
    private float averageWeight;
    @OneToMany(mappedBy = "fish")
    @JsonManagedReference
    private List<Hunting> huntings;
    @ManyToOne
    @JoinColumn(name = "level_id")
    @JsonIgnore
    private Level level;

}
