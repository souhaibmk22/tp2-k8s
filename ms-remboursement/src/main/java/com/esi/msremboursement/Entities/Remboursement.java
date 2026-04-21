package com.esi.msremboursement.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Remboursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idREmboursement;

    private LocalDate dateRemboursement;

    private Double montant;

   @ManyToOne
   private Assure assure;

    private Long idOrdonnance;
}
