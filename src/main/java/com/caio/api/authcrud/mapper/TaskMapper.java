package com.caio.api.authcrud.mapper;

import org.springframework.stereotype.Component;
import com.caio.api.authcrud.dto.TaskResponse;
import com.caio.api.authcrud.entity.Task;

@Component
public class TaskMapper {
    
    public TaskResponse toResponse(Task task) {
        return new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.getDescription()
        );
    }
}
