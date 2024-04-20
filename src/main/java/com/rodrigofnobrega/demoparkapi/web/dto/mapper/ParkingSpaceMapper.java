package com.rodrigofnobrega.demoparkapi.web.dto.mapper;

import com.rodrigofnobrega.demoparkapi.entity.ParkingSpaceEntity;
import com.rodrigofnobrega.demoparkapi.web.dto.parkingspace.ParkingSpaceCreateDTO;
import com.rodrigofnobrega.demoparkapi.web.dto.parkingspace.ParkingSpaceResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParkingSpaceMapper {
    public static ParkingSpaceEntity toParkingSpace(ParkingSpaceCreateDTO dto) {
        return new ModelMapper().map(dto, ParkingSpaceEntity.class);
    }

    public static ParkingSpaceResponseDTO toDto(ParkingSpaceEntity parkingSpace) {
        return new ModelMapper().map(parkingSpace, ParkingSpaceResponseDTO.class);
    }
}
