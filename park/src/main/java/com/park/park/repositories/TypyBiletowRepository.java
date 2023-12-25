package com.park.park.repositories;

import com.park.park.entities.TypyBiletowEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypyBiletowRepository extends JpaRepository<TypyBiletowEntity, Long> {
    Page<TypyBiletowEntity> findAll(Pageable pageable);
}
