package com.example.aftas.model.Dto.Requests;


import com.example.aftas.model.Entities.Fish;
import com.example.aftas.model.Entities.Level;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FishRequest {
    @NotBlank(message = "name must be provided")
    private String name;
    @NotEmpty(message = "weight must be provided")
    private float averageWeight;
    @NotEmpty(message = "level must be provided")
    private Integer level_id;

    public Fish toModel(){
        Level level = Level
                    .builder()
                    .code(this.level_id)
                    .build();

        return Fish
                .builder()
                .name(this.name)
                .averageWeight(this.averageWeight)
                .level(level)
                .build();
    }

}
