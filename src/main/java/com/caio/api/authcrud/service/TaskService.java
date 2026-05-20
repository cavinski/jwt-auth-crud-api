package com.caio.api.authcrud.service;

import com.caio.api.authcrud.dto.*;
import java.util.List;

public interface TaskService {
    
    TaskResponseDTO create(TaskRequestDTO request);

    List<TaskResponseDTO> findAll();

    TaskResponseDTO findById(Long id);

    TaskResponseDTO update(Long id, TaskRequestDTO request);

    void delete(Long id);
}
