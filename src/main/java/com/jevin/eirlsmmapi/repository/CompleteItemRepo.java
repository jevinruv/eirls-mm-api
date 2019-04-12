package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.CompleteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompleteItemRepo extends JpaRepository<CompleteItem, Integer> {
}
