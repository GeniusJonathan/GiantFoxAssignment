package com.giantfox.gemeenteAPI.service;


import com.giantfox.gemeenteAPI.dao.GemeenteDAO;
import com.giantfox.gemeenteAPI.exception.ResourceNotFoundException;
import com.giantfox.gemeenteAPI.model.Gemeente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GemeentenService {

    @Autowired
    @Qualifier("FakeData")
    private GemeenteDAO gemeenteDAO;

    public List<Gemeente> getAllGemeenten(String sortBy, String order) {
        if (sortBy == null || order == null) return gemeenteDAO.getAllGemeenten();

        return gemeenteDAO.getAllGemeentenSorted(sortBy, order);
    }

    public Gemeente findGemeenteById(Long gemeenteId) {
        return gemeenteDAO.getGemeenteById(gemeenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Gemeente", "id", gemeenteId));
    }


}
