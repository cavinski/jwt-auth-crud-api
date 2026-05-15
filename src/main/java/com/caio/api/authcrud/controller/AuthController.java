package com.caio.api.authcrud.controller;

import com.caio.api.authcrud.dto.UserRequest;
import com.caio.api.authcrud.dto.auth.AuthResponse;
import com.caio.api.authcrud.dto.auth.LoginRequest;
import com.caio.api.authcrud.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService service;

    @PostMapping("/register")
    public void register(@RequestBody UserRequest request) {
        service.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }
}
