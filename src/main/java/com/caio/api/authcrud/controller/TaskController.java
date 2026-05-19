package com.caio.api.authcrud.controller;

import com.caio.api.authcrud.dto.*;
import com.caio.api.authcrud.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    
    @PostMapping
    public TaskResponseDTO create(@RequestBody TaskRequestDTO request) {
        return taskService.create(request);
    }

    @GetMapping
    public List<TaskResponseDTO> findAll() {
        return taskService.findAll();
    }
}
