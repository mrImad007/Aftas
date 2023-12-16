package com.example.aftas.model.Dto;

import com.example.aftas.model.Entities.Competition;
import com.example.aftas.model.Entities.Hunting;
import com.example.aftas.model.Entities.Member;
import com.example.aftas.model.Entities.Ranking;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionDto {
    @NotEmpty(message = "code must be provided")
    private String code;
    @NotEmpty
    @FutureOrPresent
    private Date date;
    @PastOrPresent(message = "start time must be provided at least today's")
    @JsonFormat(pattern = "HH:mm:ss")
    private Time startTime;
    @Future(message = "code must be provided as a future date")
    @JsonFormat(pattern = "HH:mm:ss")
    private Time endTime;
    @NotEmpty(message = "participants number must be provided")
    private Integer numberOfParticipants;
    @NotEmpty(message = "location must be provided")
    private String location;
    @NotEmpty(message = "amount must be provided")
    private Double amount;
    private List<Hunting> huntings;
}
