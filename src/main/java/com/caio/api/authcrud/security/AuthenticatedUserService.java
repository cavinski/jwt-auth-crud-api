package com.caio.api.authcrud.security;

import com.caio.api.authcrud.entity.User;

public interface AuthenticatedUserService {
    
    User getAuthenticatedUser();
}
