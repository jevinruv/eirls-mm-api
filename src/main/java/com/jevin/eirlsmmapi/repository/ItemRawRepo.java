package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.ItemRaw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRawRepo extends JpaRepository<ItemRaw, Integer> {
}
