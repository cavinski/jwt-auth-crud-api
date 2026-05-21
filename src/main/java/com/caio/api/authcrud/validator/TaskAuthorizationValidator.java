package com.caio.api.authcrud.validator;

import com.caio.api.authcrud.entity.Task;
import com.caio.api.authcrud.entity.User;

public interface TaskAuthorizationValidator {
    
    void validate(Task task, User user);
}
