package com.caio.api.authcrud.service;

import com.caio.api.authcrud.dto.AuthResponseDTO;
import com.caio.api.authcrud.dto.LoginRequestDTO;
import com.caio.api.authcrud.dto.UserRequest;

public interface AuthService {
    
    void register(UserRequest request);

    AuthResponseDTO login(LoginRequestDTO request);
    
}
