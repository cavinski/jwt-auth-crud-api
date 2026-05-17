package com.caio.api.authcrud.controller;

import com.caio.api.authcrud.dto.UserRequest;
import com.caio.api.authcrud.dto.AuthResponseDTO;
import com.caio.api.authcrud.dto.LoginRequestDTO;
import com.caio.api.authcrud.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(service.login(request));
    }
}
