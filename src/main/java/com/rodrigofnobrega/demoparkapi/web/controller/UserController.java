package com.rodrigofnobrega.demoparkapi.web.controller;

import com.rodrigofnobrega.demoparkapi.entity.UserEntity;
import com.rodrigofnobrega.demoparkapi.enums.HttpMessagesEnum;
import com.rodrigofnobrega.demoparkapi.enums.utils.EnumUtils;
import com.rodrigofnobrega.demoparkapi.service.UserService;
import com.rodrigofnobrega.demoparkapi.web.dto.UserCreateDto;
import com.rodrigofnobrega.demoparkapi.web.dto.UserResponseDto;
import com.rodrigofnobrega.demoparkapi.web.dto.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto userCreateDto) {
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
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();

        return  ResponseEntity.ok(users);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updatePassword(@PathVariable Long id, @RequestBody UserEntity userEntity) {
        Optional<UserEntity> user = userService.updatePassword(id, userEntity.getPassword());

        return user.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(EnumUtils.enumToString(HttpMessagesEnum.USUARIO_NAO_ENCONTRADO)));
    }
}