package com.example.aftas.model.dto;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionRequest {
    @NotEmpty
    @FutureOrPresent
    private Date date;
    @NotEmpty(message = "code must be provided")
    private String code;
    @PastOrPresent(message = "start time must be provided at least today's")
    @NotEmpty(message = "start time must be provided")
    private Time startTime;
    @Future(message = "code must be provided as a future date")
    @NotEmpty(message = "end time must be provided")
    private Time endTime;
    @NotEmpty(message = "participants number must be provided")
    private Integer numberOfParticipants;
    @NotEmpty(message = "location must be provided")
    private String location;
    @NotEmpty(message = "amount must be provided")
    private Double amount;
    @NotEmpty(message = "ranking id number must be provided")
    private Integer ranking_id;
    @NotEmpty(message = "hunting id number must be provided")
    private Integer hunting_id;
}
