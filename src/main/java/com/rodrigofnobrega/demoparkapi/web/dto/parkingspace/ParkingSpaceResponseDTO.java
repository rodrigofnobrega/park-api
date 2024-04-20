package com.rodrigofnobrega.demoparkapi.web.dto.parkingspace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpaceResponseDTO {
    private Long id;
    private String code;
    private String status;
}
