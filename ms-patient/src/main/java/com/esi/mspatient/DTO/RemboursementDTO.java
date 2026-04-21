package com.esi.mspatient.DTO;

import java.time.LocalDate;

public record RemboursementDTO(Double montant, LocalDate dateRemboursement) {
}
