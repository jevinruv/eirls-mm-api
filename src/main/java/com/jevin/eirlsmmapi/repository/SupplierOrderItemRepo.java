package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.SupplierOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierOrderItemRepo extends JpaRepository<SupplierOrderItem, Integer> {
    Optional<SupplierOrderItem> findByItemRawIdAndSupplierOrderId(int itemRawId, int supplierOrderId);
}
