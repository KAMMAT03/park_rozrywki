package com.park.park.repositories;

import com.park.park.entities.PracownicyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracownicyRepository extends JpaRepository<PracownicyEntity, Long> {
    Page<PracownicyEntity> findAll(Pageable pageable);
    Page<PracownicyEntity> findAllByIdStanowiska(long idStanowiska, Pageable pageable);
}
