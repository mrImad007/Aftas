package com.example.aftas.model.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
    @Column(name = "code",unique = true, nullable = false)
    @NotEmpty
    private String code;
    @NotEmpty
    @FutureOrPresent
    private Date date;
    @NotEmpty
    @JsonFormat(pattern = "HH:mm:ss")
    private Time startTime;
    @NotEmpty
    @JsonFormat(pattern = "HH:mm:ss")
    private Time endTime;
    @NotEmpty
    private Integer numberOfParticipants;
    @NotEmpty
    private String location;
    @NotEmpty
    private Double amount;
    @OneToMany(mappedBy = "competition",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ranking> rankings;
    @OneToMany(mappedBy = "competition",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Hunting> huntings;
    @ManyToMany(mappedBy = "competitions",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Member> participants;

}
