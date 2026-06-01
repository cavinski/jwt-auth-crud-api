package com.caio.api.authcrud.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskRequest(

    @NotBlank(message = "Title is required")
    String title, 

    @NotBlank
    String description) 
    
{ }