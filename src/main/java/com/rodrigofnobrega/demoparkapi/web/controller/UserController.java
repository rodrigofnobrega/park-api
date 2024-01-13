package com.rodrigofnobrega.demoparkapi.web.controller;

import com.rodrigofnobrega.demoparkapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
public class UserController {
    private final UserService userService;
}