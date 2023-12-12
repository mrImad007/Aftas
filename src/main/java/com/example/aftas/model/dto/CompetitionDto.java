package com.example.aftas.model.dto;

import com.example.aftas.model.Entities.Hunting;
import com.example.aftas.model.Entities.Ranking;
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
    private Time startTime;
    @Future(message = "code must be provided as a future date")
    private Time endTime;
    @NotEmpty(message = "participants number must be provided")
    private Integer numberOfParticipants;
    @NotEmpty(message = "location must be provided")
    private String location;
    @NotEmpty(message = "amount must be provided")
    private Double amount;
    private List<Ranking> rankings;
    private List<Hunting> huntings;
}
