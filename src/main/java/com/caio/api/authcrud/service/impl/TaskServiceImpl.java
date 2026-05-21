package com.caio.api.authcrud.service.impl;

import com.caio.api.authcrud.dto.*;
import com.caio.api.authcrud.entity.Task;
import com.caio.api.authcrud.entity.User;
import com.caio.api.authcrud.exception.ResourceNotFoundException;
import com.caio.api.authcrud.mapper.TaskMapper;
import com.caio.api.authcrud.repository.TaskRepository;
import com.caio.api.authcrud.security.AuthenticatedUserService;
import com.caio.api.authcrud.service.TaskService;
import com.caio.api.authcrud.validator.TaskAuthorizationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final TaskMapper mapper;
    private final TaskAuthorizationValidator validator;

    @Override
    public TaskResponse create(TaskRequest request) {

        User user = authenticatedUserService.getAuthenticatedUser();
        Task task = Task.builder()
        .title(request.title())
        .description(request.description())
        .user(user)
        .build();

        Task saved = taskRepository.save(task);

        return new TaskResponse(saved.getId(),saved.getTitle(),saved.getDescription());
    }

    @Override
    public List<TaskResponse> findAll() {

        User user = authenticatedUserService.getAuthenticatedUser();

        return taskRepository
        .findByUser(user)
        .stream()
        .map(mapper::toResponse)
        .toList();
    }

    @Override
    public TaskResponse findById(Long id) {

        User user = authenticatedUserService.getAuthenticatedUser();
        Task task = taskRepository.findById(id).orElseThrow(() -> 
        new ResourceNotFoundException("Task not found"));

        validator.validate(task, user);

        return mapper.toResponse(task);
    }

    @Override
    public TaskResponse update(Long id, TaskRequest request) {

        User user = authenticatedUserService.getAuthenticatedUser();
        Task task = taskRepository.findById(id).orElseThrow(() -> 
        new ResourceNotFoundException("Task not found"));

        validator.validate(task, user);

        task.setTitle(request.title());
        task.setDescription(request.description());

        taskRepository.save(task);

        return mapper.toResponse(task);
    }

    @Override
    public void delete(Long id) {

        User user = authenticatedUserService.getAuthenticatedUser();

        Task task = taskRepository.findById(id).orElseThrow(() -> 
        new ResourceNotFoundException("Task not found"));

        validator.validate(task, user);

        taskRepository.delete(task);
    }

}