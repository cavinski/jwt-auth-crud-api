package com.caio.api.authcrud.service;

import com.caio.api.authcrud.dto.*;
import java.util.List;

public interface TaskService {
    
    TaskResponse create(TaskRequest request);

    List<TaskResponse> findAll();

    TaskResponse findById(Long id);

    TaskResponse update(Long id, TaskRequest request);

    void delete(Long id);
}
