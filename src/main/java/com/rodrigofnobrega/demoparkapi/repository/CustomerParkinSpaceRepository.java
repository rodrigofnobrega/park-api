package com.rodrigofnobrega.demoparkapi.repository;

import com.rodrigofnobrega.demoparkapi.entity.CustomerParkingSpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerParkinSpaceRepository extends JpaRepository<CustomerParkingSpaceEntity, Long> {

}
