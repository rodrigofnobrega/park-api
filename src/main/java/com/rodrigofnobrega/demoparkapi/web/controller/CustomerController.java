package com.rodrigofnobrega.demoparkapi.web.controller;

import com.rodrigofnobrega.demoparkapi.entity.CustomerEntity;
import com.rodrigofnobrega.demoparkapi.jwt.JwtUserDetails;
import com.rodrigofnobrega.demoparkapi.service.CustomerService;
import com.rodrigofnobrega.demoparkapi.service.UserService;
import com.rodrigofnobrega.demoparkapi.web.dto.customer.CustomerCreateDto;
import com.rodrigofnobrega.demoparkapi.web.dto.customer.CustomerResponseDto;
import com.rodrigofnobrega.demoparkapi.web.dto.mapper.CustomerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<CustomerResponseDto> create(@RequestBody @Valid CustomerCreateDto createDto,
                                                      @AuthenticationPrincipal JwtUserDetails userDetails) {
        CustomerEntity customer = CustomerMapper.toCustomer(createDto);
        customer.setUserEntity(userService.getById(userDetails.getId()));
        customerService.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerMapper.toDto(customer));
    }
}
