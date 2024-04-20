package com.rodrigofnobrega.demoparkapi.repository;

import com.rodrigofnobrega.demoparkapi.entity.ParkingSpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpaceEntity, Long> {
    Optional<ParkingSpaceEntity> findByCode(String code);
}
