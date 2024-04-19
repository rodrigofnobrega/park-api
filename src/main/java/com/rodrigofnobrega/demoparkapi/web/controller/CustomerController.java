package com.rodrigofnobrega.demoparkapi.web.controller;

import com.rodrigofnobrega.demoparkapi.entity.CustomerEntity;
import com.rodrigofnobrega.demoparkapi.jwt.JwtUserDetails;
import com.rodrigofnobrega.demoparkapi.repository.projection.CustomerProjection;
import com.rodrigofnobrega.demoparkapi.service.CustomerService;
import com.rodrigofnobrega.demoparkapi.service.UserService;
import com.rodrigofnobrega.demoparkapi.web.dto.customer.CustomerCreateDto;
import com.rodrigofnobrega.demoparkapi.web.dto.customer.CustomerResponseDto;
import com.rodrigofnobrega.demoparkapi.web.dto.mapper.CustomerMapper;
import com.rodrigofnobrega.demoparkapi.web.dto.mapper.PageableMapper;
import com.rodrigofnobrega.demoparkapi.web.dto.pageable.PageableDto;
import com.rodrigofnobrega.demoparkapi.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Contém todas as operações realativas ao recurso de um cliente")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final UserService userService;

    @Operation(summary = "Criar novo cliente", description = "Recurso para criar um novo cliente vinculado a um usuário cadastrado." +
            "Requisição exige uso de um bearer token. Acesso restrito a Role='CLIENTE'",
            security = @SecurityRequirement(name = "security"),
            responses = {
                @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "applicaton/json;charset=UTF-8", schema = @Schema(implementation = CustomerResponseDto.class))),
                @ApiResponse(responseCode = "409", description = "Cliente CPF já possui cadastro no sistema",
                        content = @Content(mediaType = "applicaton/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class))),
                @ApiResponse(responseCode = "422", description = "Recurso não processado por falta de dados ou dados inválidos",
                        content = @Content(mediaType = "applicaton/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class))),
                @ApiResponse(responseCode = "403", description = "Recurso não permitido ao perfil de ADMINs",
                        content = @Content(mediaType = "applicaton/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<CustomerResponseDto> create(@RequestBody @Valid CustomerCreateDto createDto,
                                                      @AuthenticationPrincipal JwtUserDetails userDetails) {
        CustomerEntity customer = CustomerMapper.toCustomer(createDto);
        customer.setUserEntity(userService.getById(userDetails.getId()));
        customerService.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerMapper.toDto(customer));
    }

    @Operation(summary = "Localizar um cliente", description = "Recurso para localizar um cliente pelo ID." +
            "Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN'",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso localizado com sucesso",
                            content = @Content(mediaType = "applicaton/json;charset=UTF-8", schema = @Schema(implementation = CustomerResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                            content = @Content(mediaType = "applicaton/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Recurso não permitido ao perfil de CLIENTE",
                            content = @Content(mediaType = "applicaton/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerResponseDto> findById(@PathVariable Long id) {
        CustomerEntity customer = customerService.findById(id);

        return ResponseEntity.ok(CustomerMapper.toDto(customer));
    }

    @Operation(summary = "Recuperar lista de clientes",
            description = "Requisição exige uso de um bearer token. Acesso restrito a Role='ADMIN'",
            security = @SecurityRequirement(name = "security"),
            parameters = {
                        @Parameter(in = ParameterIn.QUERY, name = "page",
                                content = @Content(schema = @Schema(type = "integer", defaultValue = "0")),
                                description = "Representa a página retornada"
                        ),
                        @Parameter(in = ParameterIn.QUERY, name = "size",
                                content = @Content(schema = @Schema(type = "integer", defaultValue = "20")),
                                description = "Representa o total de elementos por página"
                        ),
                        @Parameter(in = ParameterIn.QUERY, name = "sort", hidden = true,
                                array = @ArraySchema(schema = @Schema(type = "string", defaultValue = "id,asc")),
                                description = "Representa a ordenação dos resultados. Aceita múltiplos critérios de ordenação"
                        ),
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso localizado com sucesso",
                            content = @Content(mediaType = "applicaton/json;charset=UTF-8", schema = @Schema(implementation = CustomerResponseDto.class))),
                    @ApiResponse(responseCode = "403", description = "Recurso não permitido ao perfil de CLIENTE",
                            content = @Content(mediaType = "applicaton/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageableDto> findAll(@Parameter(hidden = true) @PageableDefault(size = 5, sort = {"name"})Pageable pageable) {
        Page<CustomerProjection> customer = customerService.findAll(pageable);

        return ResponseEntity.ok(PageableMapper.toDto(customer));
    }

    @Operation(summary = "Recuperar dados do cliente autenticado",
            description = "Recurso para localizar um cliente pelo ID. Requisição exige uso de um bearer token. Acesso restrito a Role='CLIENTE'",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso localizado com sucesso",
                            content = @Content(mediaType = "applicaton/json;charset=UTF-8", schema = @Schema(implementation = CustomerResponseDto.class))),
                    @ApiResponse(responseCode = "403", description = "Recurso não permitido ao perfil de ADMIN",
                            content = @Content(mediaType = "applicaton/json;charset=UTF-8", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @GetMapping("/detalhes")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<CustomerResponseDto> getDetails(@AuthenticationPrincipal JwtUserDetails userDetails) {
        CustomerEntity customer = customerService.findUserById(userDetails.getId());

        return ResponseEntity.ok(CustomerMapper.toDto(customer));
    }
}
