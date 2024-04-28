package com.rodrigofnobrega.demoparkapi.web.dto.customerparkingspace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerParkingSpaceResponseDto {
    private String carPlate;
    private String brand;
    private String model;
    private String color;
    private String customerEntityCpf;
    private String receipt;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private String parkinSpaceCode;
    private BigDecimal value;
    private BigDecimal discount;
}
