package com.rodrigofnobrega.demoparkapi.web.dto.mapper;

import com.rodrigofnobrega.demoparkapi.entity.CustomerEntity;
import com.rodrigofnobrega.demoparkapi.web.dto.customer.CustomerCreateDto;
import com.rodrigofnobrega.demoparkapi.web.dto.customer.CustomerResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerMapper {
    public static CustomerEntity toCustomer(CustomerCreateDto dto) {
        return new ModelMapper().map(dto, CustomerEntity.class);
    }

    public static CustomerResponseDto toDto(CustomerEntity customer) {
        return new ModelMapper().map(customer, CustomerResponseDto.class);
    }
}
