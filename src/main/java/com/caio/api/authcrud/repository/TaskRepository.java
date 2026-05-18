package com.caio.api.authcrud.repository;

import com.caio.api.authcrud.entity.Task;
import com.caio.api.authcrud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByUser(User user);
}