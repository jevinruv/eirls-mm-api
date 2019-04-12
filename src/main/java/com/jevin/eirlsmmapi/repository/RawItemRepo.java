package com.jevin.eirlsmmapi.repository;

import com.jevin.eirlsmmapi.model.RawItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawItemRepo extends JpaRepository<RawItem, Integer> {
}
