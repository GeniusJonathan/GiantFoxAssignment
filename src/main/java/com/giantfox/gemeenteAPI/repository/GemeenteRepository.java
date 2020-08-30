package com.giantfox.gemeenteAPI.repository;

import com.giantfox.gemeenteAPI.model.Gemeente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GemeenteRepository extends JpaRepository<Gemeente, Long> {

    List<Gemeente> findByOrderByNaamAsc();
    List<Gemeente> findByOrderByInwonersAsc();

}
