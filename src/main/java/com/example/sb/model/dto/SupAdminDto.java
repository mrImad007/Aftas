package com.example.sb.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SupAdminDto {

    private Long id;
    @Email(message = "Email was not provided")
    @NotEmpty(message = "email must be present")
    @Column(unique = true)
    private String email;
    private String password;
}
