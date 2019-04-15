package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.SupplierOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SupplierOrderItemRepo extends JpaRepository<SupplierOrderItem, Integer> {

    @Transactional
    void deleteAllBySupplierOrderId(int supplierOrderId);

    Optional<SupplierOrderItem> findByItemRawIdAndSupplierOrderStatus(int itemRawId, String supplierOrderStatus);
}
