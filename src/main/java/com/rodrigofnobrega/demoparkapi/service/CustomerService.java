package com.rodrigofnobrega.demoparkapi.service;

import com.rodrigofnobrega.demoparkapi.entity.CustomerEntity;
import com.rodrigofnobrega.demoparkapi.exception.CpfUniqueViolationException;
import com.rodrigofnobrega.demoparkapi.exception.EntityNotFoundException;
import com.rodrigofnobrega.demoparkapi.repository.CustomerRepository;
import com.rodrigofnobrega.demoparkapi.repository.projection.CustomerProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerEntity save(CustomerEntity customer) {
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException ex) {
            throw new CpfUniqueViolationException(
                    String.format("CPF '%s' não pode ser cadastrado, já existe no sistema", customer.getCpf())
            );
        }
    }

    @Transactional(readOnly = true)
    public CustomerEntity findById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cliente id=%s não encontrado no sistema", id))
        );
    }

    @Transactional(readOnly = true)
    public Page<CustomerProjection> findAll(Pageable pageable) {
        return customerRepository.findAllPageable(pageable);
    }
}
