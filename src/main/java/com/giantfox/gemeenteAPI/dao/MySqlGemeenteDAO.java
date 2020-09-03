package com.giantfox.gemeenteAPI.dao;


import com.giantfox.gemeenteAPI.model.Gemeente;
import com.giantfox.gemeenteAPI.repository.GemeenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("MySqlData")
public class MySqlGemeenteDAO implements GemeenteDAO {

    @Autowired
    private GemeenteRepository gemeenteRepository;

    public List<Gemeente> getAllGemeentenSorted(String sortBy, String order) {
        if (sortBy == null || order == null) return gemeenteRepository.findAll();

        return gemeenteRepository.findAll(createSorting(sortBy, order));
    }

    @Override
    public List<Gemeente> getAllGemeenten() {
        return gemeenteRepository.findAll();
    }

    @Override
    public Optional<Gemeente> getGemeenteById(Long id) {
        return gemeenteRepository.findById(id);
    }

    @Override
    public Gemeente insertGemeente(Gemeente gemeente) {
        return null;
    }

    @Override
    public Gemeente updateGemeente(Long id, Gemeente gemeente) {
        return null;
    }

    @Override
    public void removeGemeente(Long id) {

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
