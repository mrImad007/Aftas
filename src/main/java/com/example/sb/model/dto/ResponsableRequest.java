package com.example.sb.model.dto;

import com.example.sb.model.Entities.Admin;
import com.example.sb.model.Entities.Centre;
import com.example.sb.model.Entities.Rayon;
import com.example.sb.model.Entities.Responsable;
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
public class ResponsableRequest {
    @Email(message = "Email was not provided")
    @NotEmpty(message = "email must be present")
    @Column(unique = true)
    private String email;
    private String password;

    private Long admin_id;

    private Long rayon_id;
    public Responsable toModel(){
        Admin admin1 = Admin
                .builder()
                .id(admin_id)
                .build();
        Rayon rayon1 = Rayon
                .builder()
                .id(rayon_id)
                .build();
        return Responsable.
                builder()
                .email(this.email)
                .password(this.password)
                .admin(admin1)
                .rayon(rayon1)
                .build();
    }

}
