package com.rodrigofnobrega.demoparkapi.web.dto.mapper;

import com.rodrigofnobrega.demoparkapi.entity.CustomerParkingSpaceEntity;
import com.rodrigofnobrega.demoparkapi.entity.ParkingSpaceEntity;
import com.rodrigofnobrega.demoparkapi.web.dto.parkingspace.ParkingSpaceCreateDTO;
import com.rodrigofnobrega.demoparkapi.web.dto.parkingspace.ParkingSpaceResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerParkingSpaceMapper {
    public static CustomerParkingSpaceEntity toParkingSpace(ParkingSpaceCreateDTO data) {
        return new ModelMapper().map(data, CustomerParkingSpaceEntity.class);
    }

    public static ParkingSpaceResponseDTO toDto(CustomerParkingSpaceEntity data) {
        return new ModelMapper().map(data, ParkingSpaceResponseDTO.class);
    }
}
