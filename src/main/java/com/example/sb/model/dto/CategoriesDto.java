package com.example.sb.model.dto;

import com.example.sb.model.Entities.Rayon;
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
public class CategoriesDto {
    private Long id;
    @NotBlank(message = "Category name cannot be blank")
    @Size(max = 255, message = "Category name cannot exceed 255 characters")
    private String categorie;
    private Rayon rayon;
}
