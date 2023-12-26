package com.park.park.repositories;

import com.park.park.entities.RodzajeMiejscaParkingowegoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RodzajeMiejscaParkingowegoRepository extends JpaRepository<RodzajeMiejscaParkingowegoEntity, Long> {
    Page<RodzajeMiejscaParkingowegoEntity> findAll(Pageable pageable);
}
