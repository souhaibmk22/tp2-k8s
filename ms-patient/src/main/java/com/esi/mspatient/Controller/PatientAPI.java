package com.esi.mspatient.Controller;


import com.esi.mspatient.DTO.OrdonnanceDTO;
import com.esi.mspatient.DTO.RemboursementDTO;
import com.esi.mspatient.Entities.Patient;
import com.esi.mspatient.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api")
public class PatientAPI {

    final PatientRepository patientRepository;
    final HttpGraphQlClient ordonnanceClient;
    final HttpGraphQlClient remboursementClient;


    public PatientAPI(
            PatientRepository patientRepository,
            @Qualifier("OrdonnanceGraphQlClient") HttpGraphQlClient ordonnanceClient,
            @Qualifier("RemboursementGraphQlClient") HttpGraphQlClient remboursementClient
    ) {
        this.ordonnanceClient = ordonnanceClient;
        this.remboursementClient = remboursementClient;
        this.patientRepository = patientRepository;

    }

    @GetMapping("patient/{idp}")
    public Patient getPatientWithOrdonannce(@PathVariable("idp") Long idPatient) {

        Patient patient = patientRepository.findById(idPatient)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));


        String queryOrdonnance = " {  getOrdonnanceByIdPatient(idPatient: " + idPatient + ")  " +
                                 " { idOrdonnance      medicaments {  nom  cout }} }";

       List<OrdonnanceDTO> ordonnances = ordonnanceClient.document(queryOrdonnance)
                .retrieve("getOrdonnanceByIdPatient")
                .toEntityList(OrdonnanceDTO.class)
                .block();

       ordonnances.forEach(o->{
           String queryRemboursement="{  getRemboursementByIdOrdonnance (idOrdonnance:"+o.getIdOrdonnance()+")" +
                   "{ montant dateRemboursement}}";

           RemboursementDTO remboursementDTO=remboursementClient.document(queryRemboursement)
                   .retrieve("getRemboursementByIdOrdonnance")
                   .toEntity(RemboursementDTO.class)
                   .block();

           o.setRemboursementDTO(remboursementDTO);
       });
        patient.setOrdonnances(ordonnances);
        return patient;
    }


}
