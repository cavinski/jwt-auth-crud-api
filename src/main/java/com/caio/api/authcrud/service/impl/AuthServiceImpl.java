package com.caio.api.authcrud.service.impl;

import com.caio.api.authcrud.dto.UserRequest;
import com.caio.api.authcrud.dto.auth.AuthResponse;
import com.caio.api.authcrud.dto.auth.LoginRequest;
import com.caio.api.authcrud.entity.User;
import com.caio.api.authcrud.repository.UserRepository;
import com.caio.api.authcrud.security.JwtService;
import com.caio.api.authcrud.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(UserRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials!");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}
