package com.park.park.repositories;

import com.park.park.entities.KlienciEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlienciRepository extends JpaRepository<KlienciEntity, Long>{
    Page<KlienciEntity> findAll(Pageable pageable);
}
