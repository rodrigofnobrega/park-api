package com.rodrigofnobrega.demoparkapi.web.controller;

import com.rodrigofnobrega.demoparkapi.entity.UserEntity;
import com.rodrigofnobrega.demoparkapi.enums.HttpMessagesEnum;
import com.rodrigofnobrega.demoparkapi.enums.utils.EnumUtils;
import com.rodrigofnobrega.demoparkapi.service.UserService;
import com.rodrigofnobrega.demoparkapi.web.dto.UserCreateDto;
import com.rodrigofnobrega.demoparkapi.web.dto.UserPasswordDto;
import com.rodrigofnobrega.demoparkapi.web.dto.UserResponseDto;
import com.rodrigofnobrega.demoparkapi.web.dto.mapper.UserMapper;
import com.rodrigofnobrega.demoparkapi.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Usuarios",
     description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de um usuário."
)
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Criar um novo usuário",
            description = "Recurso para criar um novo usuário",
            responses = {
                @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json",
                                        schema = @Schema(implementation = UserResponseDto.class))),
                @ApiResponse(responseCode = "409", description = "Usuário já cadastrado no sistema",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ErrorMessage.class))),
                @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválidos",
                        content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserEntity user = userService.save(UserMapper.toUser(userCreateDto));

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Optional<UserEntity> user  = userService.getById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(EnumUtils.enumToString(HttpMessagesEnum.USUARIO_NAO_ENCONTRADO));
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(UserMapper.toDto(user.get()));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();

        return  ResponseEntity.ok(UserMapper.toListDto(users));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UserPasswordDto userPasswordDto) {
        Optional<UserEntity> user = userService.updatePassword(id, userPasswordDto.getCurrentPassword(), userPasswordDto.getNewPassword(), userPasswordDto.getConfirmPassword());

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}