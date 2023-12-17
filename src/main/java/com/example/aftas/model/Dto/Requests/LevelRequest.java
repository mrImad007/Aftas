package com.example.aftas.model.Dto.Requests;

import com.example.aftas.model.Entities.Level;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LevelRequest {
    @NotEmpty(message = "code must be provided")
    private Integer code;
    @NotBlank(message = "description must be provided")
    private String description;
    @NotEmpty(message = "points must be provided")
    private Integer points;

    public Level toModel(){
        return Level
                .builder()
                .code(this.code)
                .description(this.description)
                .points(this.points)
                .build();
    }
}
