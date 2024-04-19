package com.rodrigofnobrega.demoparkapi.repository;

import com.rodrigofnobrega.demoparkapi.entity.CustomerEntity;
import com.rodrigofnobrega.demoparkapi.repository.projection.CustomerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query("select c from CustomerEntity c")
    Page<CustomerProjection> findAllPageable(Pageable pageable);

    CustomerEntity findByUserEntityId(Long id);
}
