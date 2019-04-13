package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.ItemCompleteLowQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCompleteLowQuantityRepo extends JpaRepository<ItemCompleteLowQuantity, Integer> {
}
