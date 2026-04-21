package com.esi.msordonnance.Proxy;

import com.esi.msordonnance.DTO.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ms-patient", url="localhost:8081")
public interface PatientProxy {

    @GetMapping("/patients/{idp}")
    public PatientDTO getPatient(@PathVariable("idp") Long idp);
}
