package com.park.park.repositories;

import com.park.park.entities.MiejscaParkingoweEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiejscaParkingoweRepository extends JpaRepository<MiejscaParkingoweEntity, Long> {
    Page<MiejscaParkingoweEntity> findAll(Pageable pageable);
    Page<MiejscaParkingoweEntity> findAllByIdRodzajuMiejscaParkingowego(
            long idRodzajuMiejscaParkingowego, Pageable pageable);
}
