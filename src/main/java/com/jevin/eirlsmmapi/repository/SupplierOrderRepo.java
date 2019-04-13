package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierOrderRepo extends JpaRepository<SupplierOrder, Integer> {
}
