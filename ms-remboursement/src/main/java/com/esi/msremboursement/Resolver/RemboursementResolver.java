package com.esi.msremboursement.Resolver;

import com.esi.msremboursement.Entities.Remboursement;
import com.esi.msremboursement.Repository.RemboursementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RemboursementResolver {
    final RemboursementRepository remboursementRepository;

    @QueryMapping
    public Remboursement getRemboursementByIdOrdonnance (@Argument Long idOrdonnance)
    {
        return remboursementRepository.findRemboursementByIdOrdonnance(idOrdonnance);
    }
}
