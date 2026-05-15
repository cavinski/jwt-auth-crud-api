package com.caio.api.authcrud.service;

import com.caio.api.authcrud.dto.auth.AuthResponse;
import com.caio.api.authcrud.dto.auth.LoginRequest;
import com.caio.api.authcrud.dto.UserRequest;

public interface AuthService {
    
    void register(UserRequest request);

    AuthResponse login(LoginRequest request);
    
}
