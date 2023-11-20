package com.example.sb.model.Entities;

import jakarta.persistence.*;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class SupAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
}
