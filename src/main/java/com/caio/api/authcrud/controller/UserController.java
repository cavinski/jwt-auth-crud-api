package com.caio.api.authcrud.controller;

import com.caio.api.authcrud.dto.UserRequest;
import com.caio.api.authcrud.dto.UserResponse;
import com.caio.api.authcrud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService service;

    @PostMapping
    public UserResponse create(@RequestBody UserRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return service.findAll();
    }
}
