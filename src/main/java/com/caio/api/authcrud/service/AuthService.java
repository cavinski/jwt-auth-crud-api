package com.caio.api.authcrud.service;

import com.caio.api.authcrud.dto.auth.*;

public interface AuthService {
    
    void register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
    
}
