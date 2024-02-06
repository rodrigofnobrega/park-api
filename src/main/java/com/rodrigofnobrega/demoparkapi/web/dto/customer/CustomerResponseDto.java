package com.rodrigofnobrega.demoparkapi.web.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerResponseDto {
    private Long id;
    private String name;
    private String cpf;
}
