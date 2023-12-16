package com.example.aftas.model.Dto.Requests;

import com.example.aftas.model.Entities.Competition;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    public Competition toModel(){

        return Competition.
                builder()
                .code(this.code)
                .date(this.date)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .numberOfParticipants(this.numberOfParticipants)
                .location(this.location)
                .amount(this.amount)
                .build();
    }
}
