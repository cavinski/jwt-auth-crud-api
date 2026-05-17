package com.caio.api.authcrud.service.impl;

import com.caio.api.authcrud.dto.UserRequest;
import com.caio.api.authcrud.dto.AuthResponseDTO;
import com.caio.api.authcrud.dto.LoginRequestDTO;
import com.caio.api.authcrud.entity.User;
import com.caio.api.authcrud.repository.UserRepository;
import com.caio.api.authcrud.security.JwtService;
import com.caio.api.authcrud.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void register(UserRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        repository.save(user);
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
            )
        );

        String token = jwtService.generateToken(request.email());

        return new AuthResponseDTO(token);
    }
}
