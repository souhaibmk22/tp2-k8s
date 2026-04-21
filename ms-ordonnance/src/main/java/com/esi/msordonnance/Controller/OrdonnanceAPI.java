package com.esi.msordonnance.Controller;

import com.esi.msordonnance.DTO.PatientDTO;
import com.esi.msordonnance.Entities.Ordonnance;
import com.esi.msordonnance.Proxy.PatientProxy;
import com.esi.msordonnance.Proxy.RemboursementProxy;
import com.esi.msordonnance.Repository.OrdonnanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class OrdonnanceAPI {

    final OrdonnanceRepository ordonnanceRepository;
    final PatientProxy patientProxy;
    final RemboursementProxy remboursementProxy;


    @GetMapping("ordonnance/{ido}")
    public Ordonnance getOrdonnance(@PathVariable("ido") Long ido) {
      Ordonnance ordonnance=ordonnanceRepository.findById(ido).get();

      ordonnance.setPatient(patientProxy.getPatient(ordonnance.getIdPatient()));

      ordonnance.setRemboursement(remboursementProxy.getReboursement(
                                                                    ordonnance.getIdRemboursement(),
                                                           "toOrd"));


      return ordonnance;
    }

    @GetMapping("ordonnance2/{ido}")
    public ResponseEntity<Ordonnance> getOrdonnance2(@PathVariable("ido") Long ido) {
        Optional<Ordonnance> ordonnanceOptional = ordonnanceRepository.findById(ido);

        if (ordonnanceOptional.isPresent()) {
            Ordonnance ordonnance = ordonnanceOptional.get();

            PatientDTO patient = patientProxy.getPatient(ordonnance.getIdPatient());

            if (patient != null) {
                ordonnance.setPatient(patient);
            } else {
                // Handle the case where the patient is not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok(ordonnance);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
   }
