package com.esi.msordonnance.Resolver;

import com.esi.msordonnance.Entities.Medicament;
import com.esi.msordonnance.Entities.Ordonnance;
import com.esi.msordonnance.Repository.OrdonnanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrdonnanceResolver {

    private final OrdonnanceRepository ordonnanceRepository;

    @QueryMapping
    public Ordonnance getOrdonnanceById(@Argument Long id) {
        return ordonnanceRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Ordonnance> getAllOrdonnances() {
        return ordonnanceRepository.findAll();
    }

    @QueryMapping
    public List<Ordonnance> getOrdonnanceByIdPatient(@Argument Long idPatient) {
        return ordonnanceRepository.findOrdonnancesByIdPatient(idPatient);
    }

    @SchemaMapping(typeName = "Ordonnance", field = "medicaments")
    public List<Medicament> getMedicaments(Ordonnance ordonnance) {
        return ordonnance.getMedicaments();
    }


}