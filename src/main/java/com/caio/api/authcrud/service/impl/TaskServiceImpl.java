package com.caio.api.authcrud.service.impl;

import com.caio.api.authcrud.dto.*;
import com.caio.api.authcrud.entity.Task;
import com.caio.api.authcrud.entity.User;
import com.caio.api.authcrud.exception.ResourceNotFoundException;
import com.caio.api.authcrud.exception.UnauthorizedTaskAccessException;
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

        User user = getAuthenticadetUser();

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

        User user = getAuthenticadetUser();

        return taskRepository
        .findByUser(user)
        .stream()
        .map(this::convertToResponse)
        .toList();
    }

    @Override
    public TaskResponseDTO findById(Long id) {

        User user = getAuthenticadetUser();

        Task task = taskRepository.findById(id).orElseThrow(() -> 
        new ResourceNotFoundException("Task not found"));

        validateOwner(task, user);

        return convertToResponse(task);
    }

    @Override
    public TaskResponseDTO update(Long id, TaskRequestDTO request) {

        User user = getAuthenticadetUser();

        Task task = taskRepository.findById(id).orElseThrow(() -> 
        new ResourceNotFoundException("Task not found"));

        validateOwner(task, user);

        task.setTitle(request.title());
        task.setDescription(request.description());

        taskRepository.save(task);

        return convertToResponse(task);
    }

    @Override
    public void delete(Long id) {

        User user = getAuthenticadetUser();

        Task task = taskRepository.findById(id).orElseThrow(() -> 
        new ResourceNotFoundException("Task not found"));

        validateOwner(task, user);

        taskRepository.delete(task);
    }

    

    private User getAuthenticadetUser() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email).orElseThrow(() -> 
        new ResourceNotFoundException("user not found"));
    }

    private void validateOwner(Task task, User user) {
        
        if (!task.getUser().getId().equals(user.getId())) {
            
            throw new UnauthorizedTaskAccessException("Access denied");
        }
    }

    private TaskResponseDTO convertToResponse(Task task) {
        return new TaskResponseDTO(
            task.getId(),
            task.getTitle(),
            task.getDescription()
        );
    }
    
}