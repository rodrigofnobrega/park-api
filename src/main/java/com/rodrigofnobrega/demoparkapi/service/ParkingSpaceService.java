package com.rodrigofnobrega.demoparkapi.service;

import com.rodrigofnobrega.demoparkapi.entity.ParkingSpaceEntity;
import com.rodrigofnobrega.demoparkapi.exception.EntityNotFoundException;
import com.rodrigofnobrega.demoparkapi.repository.ParkingSpaceRepository;
import com.rodrigofnobrega.demoparkapi.web.exception.CodeUniqueViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkingSpaceService {
    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @Transactional
    public ParkingSpaceEntity save(ParkingSpaceEntity parkingSpace) {
        try {
            return parkingSpaceRepository.save(parkingSpace);
        } catch (DataIntegrityViolationException ex) {
            throw new CodeUniqueViolationException(String.format("Vaga com códgio='%s' já cadastrada.", parkingSpace.getCode()));
        }
    }

    @Transactional(readOnly = true)
    public ParkingSpaceEntity findByCode(String code) {
        return parkingSpaceRepository.findByCode(code).orElseThrow(
                () -> new EntityNotFoundException(String.format("Vaga com código='%s não foi encontrada.", code))
        );
    }
}
