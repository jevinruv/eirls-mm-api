package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.ItemComplete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCompleteRepo extends JpaRepository<ItemComplete, Integer> {
}
