package com.caio.api.authcrud.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @GetMapping("/me")
    public Map<String,String> me(Authentication authentication) {
        return Map.of("email", authentication.getName());
    } 
}
