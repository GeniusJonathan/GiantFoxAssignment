package com.giantfox.gemeenteAPI.dao;

import com.giantfox.gemeenteAPI.model.Gemeente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Qualifier("FakeData")
public class FakeGemeenteDAO implements GemeenteDAO {

    private List<Gemeente> gemeenteList = new ArrayList<>();

    @Override
    public List<Gemeente> getAllGemeenten() {
        return this.gemeenteList;
    }

    @Override
    public List<Gemeente> getAllGemeentenSorted(String sortBy, String order) {

        if( !sortBy.equalsIgnoreCase("inwoners") && !sortBy.equalsIgnoreCase("naam")) {
            return getAllGemeenten().stream().sorted(Comparator.comparing(Gemeente::getNaam)).collect(Collectors.toList());
        }

        if (order.equalsIgnoreCase("desc")) {
            if (sortBy.equalsIgnoreCase("inwoners")){
                return getAllGemeenten().stream().sorted(Comparator.comparing(Gemeente::getInwoners).reversed()).collect(Collectors.toList());

            }

            if (sortBy.equalsIgnoreCase("naam")){
                return getAllGemeenten().stream().sorted(Comparator.comparing(Gemeente::getNaam).reversed()).collect(Collectors.toList());

            }
        }

        if (order.equalsIgnoreCase("asc")) {
            if (sortBy.equalsIgnoreCase("inwoners")){
                return getAllGemeenten().stream().sorted(Comparator.comparing(Gemeente::getInwoners)).collect(Collectors.toList());

            }

            if (sortBy.equalsIgnoreCase("naam")){
                return getAllGemeenten().stream().sorted(Comparator.comparing(Gemeente::getNaam)).collect(Collectors.toList());

            }
        }

        return getAllGemeenten().stream().sorted(Comparator.comparing(Gemeente::getNaam)).collect(Collectors.toList());
    }

    @Override
    public Optional<Gemeente> getGemeenteById(Long id) {
        return this.gemeenteList.stream().filter( x -> x.getId() == id).findFirst();
    }

    @Override
    public Gemeente insertGemeente(Gemeente gemeente) {
        if(this.gemeenteList.add(gemeente)) {
            return gemeente;
        } else {
            return null;
        }
    }

    @Override
    public Gemeente updateGemeente(Long id, Gemeente gemeente) {
        return null;
    }

    @Override
    public void removeGemeente(Long id) {

    }
}
