package com.caio.api.authcrud.service;

import com.caio.api.authcrud.dto.UserRequest;
import com.caio.api.authcrud.dto.UserResponse;

import java.util.List;

public interface UserService {
    
    UserResponse create(UserRequest request);

    List<UserResponse> findAll();
}
