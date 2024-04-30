package com.rodrigofnobrega.demoparkapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {
    @Autowired
    private CustomerParkinSpaceService customerParkinSpaceService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ParkingSpaceService parkingSpaceService;
}
