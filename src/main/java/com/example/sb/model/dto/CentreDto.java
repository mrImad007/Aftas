package com.example.sb.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CentreDto {

    private Long id;
    @NotBlank(message = "Centre name cannot be blank")
    @Size(max = 255, message = "Centre name cannot exceed 255 characters")
    private String name;

}
