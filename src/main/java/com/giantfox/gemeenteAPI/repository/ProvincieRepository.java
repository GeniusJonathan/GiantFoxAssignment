package com.giantfox.gemeenteAPI.repository;

import com.giantfox.gemeenteAPI.model.Provincie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvincieRepository extends JpaRepository<Provincie, Long> {

}
