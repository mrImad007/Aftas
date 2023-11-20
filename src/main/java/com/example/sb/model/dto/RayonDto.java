package com.example.sb.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RayonDto {

    private Long id;
    @NotBlank(message = "Rayon name cannot be blank")
    @Size(max = 255, message = "Rayon name cannot exceed 255 characters")
    private String name;

}
