package com.park.park.repositories;

import com.park.park.entities.BiletyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiletyRepository extends JpaRepository<BiletyEntity, Long> {
    Page<BiletyEntity> findAll(Pageable pageable);
    Page<BiletyEntity> findAllByIdKlienta(long idKlienta, Pageable pageable);
}
