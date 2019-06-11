package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierOrderRepo extends JpaRepository<SupplierOrder, Integer> {
    Optional<SupplierOrder> findByIdAndStatus(int id, String status);
    Optional<List<SupplierOrder>> findAllByStatus(String status);
}
