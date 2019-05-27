package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalesOrderRepo extends JpaRepository<SalesOrder, Integer> {
    Optional<SalesOrder> findBySalesOrderId(int salesOrderId);
}
