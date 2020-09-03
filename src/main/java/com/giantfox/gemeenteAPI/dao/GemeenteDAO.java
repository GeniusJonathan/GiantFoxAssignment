package com.giantfox.gemeenteAPI.dao;

import com.giantfox.gemeenteAPI.model.Gemeente;

import java.util.List;
import java.util.Optional;

public interface GemeenteDAO {
    List<Gemeente> getAllGemeenten();
    List<Gemeente> getAllGemeentenSorted(String sortBy, String order);
    Optional<Gemeente> getGemeenteById(Long id);
    Gemeente insertGemeente(Gemeente gemeente);
    Gemeente updateGemeente(Long id, Gemeente gemeente);
    void removeGemeente(Long id);
}
