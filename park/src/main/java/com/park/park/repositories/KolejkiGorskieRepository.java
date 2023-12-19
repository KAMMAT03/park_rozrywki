package com.park.park.repositories;

import com.park.park.entities.KolejkiGorskieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KolejkiGorskieRepository extends JpaRepository<KolejkiGorskieEntity, Long> {
    Page<KolejkiGorskieEntity> findAll(Pageable pageable);
}
