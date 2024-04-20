package com.rodrigofnobrega.demoparkapi.web.controller;

import com.rodrigofnobrega.demoparkapi.entity.ParkingSpaceEntity;
import com.rodrigofnobrega.demoparkapi.service.ParkingSpaceService;
import com.rodrigofnobrega.demoparkapi.web.dto.mapper.ParkingSpaceMapper;
import com.rodrigofnobrega.demoparkapi.web.dto.parkingspace.ParkingSpaceCreateDTO;
import com.rodrigofnobrega.demoparkapi.web.dto.parkingspace.ParkingSpaceResponseDTO;
import com.rodrigofnobrega.demoparkapi.web.dto.user.UserResponseDto;
import com.rodrigofnobrega.demoparkapi.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @Operation(summary = "Criar uma nova vaga", description = "Recurso para criar uma nova vaga." +
            "Requisição exige o uso de um bearer token. Acesso restrito a Role='ADMIN'",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            headers = @Header(name = HttpHeaders.LOCATION, description = "URL do recurso criado")),
                    @ApiResponse(responseCode = "403", description = "Usuário sem permissão de acesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(responseCode = "409", description = "Vaga já cadastrada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Recurso não processado por falta de dados ou dados inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            })
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

    @Operation(summary = "Localizar uma vaga", description = "Recurso para retornar uma vaga pelo seu código." +
            "Requisição exige o uso de um bearer token. Acesso restrito a Role='ADMIN'" ,
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso localizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParkingSpaceResponseDTO.class))),
                    @ApiResponse(responseCode = "403", description = "Usuário sem permissão de acesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Vaga não localizada",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            })
    @GetMapping("/{code}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParkingSpaceResponseDTO> findByCode(@PathVariable String code) {
        ParkingSpaceEntity parkingSpace = parkingSpaceService.findByCode(code);
        return ResponseEntity.ok(ParkingSpaceMapper.toDto(parkingSpace));
    }
}
