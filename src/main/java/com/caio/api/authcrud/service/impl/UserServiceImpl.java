package com.caio.api.authcrud.service.impl;

import com.caio.api.authcrud.dto.UserRequest;
import com.caio.api.authcrud.dto.UserResponse;
import com.caio.api.authcrud.entity.User;
import com.caio.api.authcrud.repository.UserRepository;
import com.caio.api.authcrud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    
    private final UserRepository repository;

    @Override
    public UserResponse create(UserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = repository.save(user);

        return mapToResponse(saved);
    }

    @Override
    public List<UserResponse> findAll() {
        return repository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        return response;
    }
}
