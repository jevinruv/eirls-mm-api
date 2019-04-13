package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.ItemRawLowQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRawLowQuantityRepo extends JpaRepository<ItemRawLowQuantity, Integer> {
}
