package com.park.park.repositories;

import com.park.park.entities.AtrakcjeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtrakcjeRepository extends JpaRepository<AtrakcjeEntity, Long> {
    Page<AtrakcjeEntity> findAll(Pageable pageable);
    Page<AtrakcjeEntity> findAllByTypAtrakcjiIsNotIn(List<String> list, Pageable pageable);
}
