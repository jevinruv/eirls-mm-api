package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.SupplierOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SupplierOrderItemRepo extends JpaRepository<SupplierOrderItem, Integer> {

    @Transactional
    void deleteAllBySupplierOrderId(int supplierOrderId);
}
