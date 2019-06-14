package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.Bom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BomRepo extends JpaRepository<Bom, Integer> {
    Optional<Bom> findByIdAndStatus(int id, String status);
    Optional<List<Bom>> findAllByStatus(String status);
    Optional<Bom> findByProductionBomId(String productionBomId);

}
