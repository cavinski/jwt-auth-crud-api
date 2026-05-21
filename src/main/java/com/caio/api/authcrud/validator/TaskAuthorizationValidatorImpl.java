package com.caio.api.authcrud.validator;

import com.caio.api.authcrud.entity.Task;
import com.caio.api.authcrud.entity.User;
import com.caio.api.authcrud.exception.UnauthorizedTaskAccessException;
import org.springframework.stereotype.Component;

@Component
public class TaskAuthorizationValidatorImpl implements TaskAuthorizationValidator{
    
    @Override
    public void validate(Task task, User user) {
        if(!task.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedTaskAccessException("Access Denied");
        }
    }
}
