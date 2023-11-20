package com.example.sb.model.dto;

import com.example.sb.model.Entities.Admin;
import com.example.sb.model.Entities.Centre;
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
public class AdminRequest {
    @Email(message = "Email was not provided")
    @NotEmpty(message = "email must be present")
    @Column(unique = true)
    private String email;
    private String password;
    private long centre_id;

    public Admin toModel(){
        Centre center = Centre
                .builder()
                .id(centre_id)
                .build();
        return Admin.
                builder()
                .email(this.email)
                .password(this.password)
                .centre(center)
                .build();
    }

}