package com.rodrigofnobrega.demoparkapi.web.controller;

import com.rodrigofnobrega.demoparkapi.entity.UserEntity;
import com.rodrigofnobrega.demoparkapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity userSaved = userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Optional<UserEntity> user  = userService.getById(id);

        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");

        }

        return  ResponseEntity.ok(user.get());
    }
}