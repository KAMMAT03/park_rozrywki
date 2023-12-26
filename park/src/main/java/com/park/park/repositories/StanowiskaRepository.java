package com.park.park.repositories;

import com.park.park.entities.StanowiskaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StanowiskaRepository extends JpaRepository<StanowiskaEntity, Long> {
    Page<StanowiskaEntity> findAll(Pageable pageable);
}
