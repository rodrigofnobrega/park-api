package com.rodrigofnobrega.demoparkapi.service;

import com.rodrigofnobrega.demoparkapi.entity.CustomerParkingSpaceEntity;
import com.rodrigofnobrega.demoparkapi.repository.CustomerParkinSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerParkinSpaceService {
    @Autowired
    private CustomerParkinSpaceRepository customerParkinSpaceRepository;

    @Transactional
    public CustomerParkingSpaceEntity save(CustomerParkingSpaceEntity customerParkingSpaceEntity) {
        return customerParkinSpaceRepository.save(customerParkingSpaceEntity);
    }
}
