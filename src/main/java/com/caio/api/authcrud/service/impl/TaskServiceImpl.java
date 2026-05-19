package com.caio.api.authcrud.service.impl;

import com.caio.api.authcrud.dto.*;
import com.caio.api.authcrud.entity.Task;
import com.caio.api.authcrud.entity.User;
import com.caio.api.authcrud.repository.TaskRepository;
import com.caio.api.authcrud.repository.UserRepository;
import com.caio.api.authcrud.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponseDTO create(TaskRequestDTO request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        Task task = Task.builder()
        .title(request.title())
        .description(request.description())
        .user(user)
        .build();

        Task saved = taskRepository.save(task);

        return new TaskResponseDTO(saved.getId(),saved.getTitle(),saved.getDescription());
    }

    @Override
    public List<TaskResponseDTO> findAll() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow();

        return taskRepository
        .findByUser(user)
        .stream()
        .map(task -> new TaskResponseDTO(task.getId(),task.getTitle(),task.getDescription()))
        .toList();
    }
    
}