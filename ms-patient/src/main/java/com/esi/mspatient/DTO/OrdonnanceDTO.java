package com.esi.mspatient.DTO;


import java.util.List;

import lombok.Data;

import java.util.List;

@Data
public class OrdonnanceDTO {
    private Long idOrdonnance;
    private List<MedicamentInfo> medicaments;
    private RemboursementDTO remboursementDTO;

}



