package com.caio.api.authcrud.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @GetMapping
    public List<String> getTasks() {
        return List.of(
            "Taks 1",
            "Task 2",
            "Task 3"
        );
    }
}
