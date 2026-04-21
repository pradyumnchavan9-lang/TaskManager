package com.backend.TaskManager.service;

import com.backend.TaskManager.dto.UserResponse;
import com.backend.TaskManager.entity.Task;
import com.backend.TaskManager.entity.User;
import com.backend.TaskManager.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUser(){

        UserResponse userResponse = new UserResponse();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername()).
                orElseThrow(() -> new RuntimeException("Username not found"));
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setRole(user.getRole());
        List<Task> tasks = user.getTasks();
        List<Long> taskIds = new ArrayList<>();
        for (Task task : tasks) {
            taskIds.add(task.getId());
        }
        userResponse.setTaskIds(taskIds);
        return userResponse;
    }
}
