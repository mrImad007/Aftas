package com.example.sb.model.Entities;
import com.example.sb.model.Enum.Statut;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Builder
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Promotions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_responsable")
    private Responsable responsable;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_categorie")
    private Categories categorie;
    @ManyToOne
    @JoinColumn(name = "id_produit")
    private Produits produit;
    private LocalDate datepromo;
    private Long reduction;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    private Integer quantity;

}
