package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.SalesOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderItemRepo extends JpaRepository<SalesOrderItem, Integer> {
}
