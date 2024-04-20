package com.rodrigofnobrega.demoparkapi.repository;

import com.rodrigofnobrega.demoparkapi.entity.ParkingSpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkinSpaceRepository extends JpaRepository<ParkingSpaceEntity, Long> {
}
