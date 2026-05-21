package com.caio.api.authcrud.security;

import com.caio.api.authcrud.entity.User;
import com.caio.api.authcrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserServiceImpl implements AuthenticatedUserService{
    
    private final UserRepository userRepository;

    @Override
    public User getAuthenticatedUser() {
        
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow();
    }
}
