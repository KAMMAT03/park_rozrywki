package com.park.park.repositories;

import com.park.park.entities.GastronomieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastronomieRepository extends JpaRepository<GastronomieEntity, Long> {
    Page<GastronomieEntity> findAll(Pageable pageable);
}
