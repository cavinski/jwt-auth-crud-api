package com.caio.api.authcrud.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

    @NotBlank(message = "Name is required")
    String name,

    @Email(message = "Invalid email")
    @NotBlank(message = "Email required")
    String email,

    @Size(min=6, message = "Password min 6 chars")
    String password
    
) { }
