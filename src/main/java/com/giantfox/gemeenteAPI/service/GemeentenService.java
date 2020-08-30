package com.giantfox.gemeenteAPI.service;


import com.giantfox.gemeenteAPI.exception.ResourceNotFoundException;
import com.giantfox.gemeenteAPI.model.Gemeente;
import com.giantfox.gemeenteAPI.repository.GemeenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GemeentenService {

    @Autowired
    private GemeenteRepository gemeenteRepository;

    public List<Gemeente> getAllGemeeenten(String sortBy, String order) {
        if (sortBy == null || order == null) return gemeenteRepository.findAll();

        return gemeenteRepository.findAll(createSorting(sortBy, order));
    }

    public Gemeente findGemeenteById(Long gemeenteId) {
        return gemeenteRepository.findById(gemeenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Gemeente", "id", gemeenteId));
    }

    private Sort createSorting(String sortBy, String order){
        if( !sortBy.equalsIgnoreCase("inwoners") && !sortBy.equalsIgnoreCase("naam")) {
            return Sort.by("naam").ascending();
        }

        if (order.equalsIgnoreCase("desc")) {
            return Sort.by(sortBy).descending();
        }

        if (order.equalsIgnoreCase("asc")) {
            return Sort.by(sortBy).ascending();
        }

        return Sort.by("naam").ascending();

    }
}
