package com.rodrigofnobrega.demoparkapi.web.controller;

import com.rodrigofnobrega.demoparkapi.entity.ParkingSpaceEntity;
import com.rodrigofnobrega.demoparkapi.service.ParkingSpaceService;
import com.rodrigofnobrega.demoparkapi.web.dto.mapper.ParkingSpaceMapper;
import com.rodrigofnobrega.demoparkapi.web.dto.parkingspace.ParkingSpaceCreateDTO;
import com.rodrigofnobrega.demoparkapi.web.dto.parkingspace.ParkingSpaceResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/vagas")
@RequiredArgsConstructor
public class ParkingSpaceController {
    @Autowired
    private ParkingSpaceService parkingSpaceService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> create(@RequestBody @Valid ParkingSpaceCreateDTO dto) {
        ParkingSpaceEntity parkingSpace = ParkingSpaceMapper.toParkingSpace(dto);
        parkingSpaceService.save(parkingSpace);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(parkingSpace.getCode())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParkingSpaceResponseDTO> findByCode(@PathVariable String code) {
        ParkingSpaceEntity parkingSpace = parkingSpaceService.findByCode(code);
        return ResponseEntity.ok(ParkingSpaceMapper.toDto(parkingSpace));
    }
}
