package com.example.sb.model.dto;

import com.example.sb.model.Entities.Centre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDto {
    @Email(message = "Email was not provided")
    @NotEmpty(message = "email must be present")
    @Column(unique = true)
    private String email;
    private Centre centre;
}
