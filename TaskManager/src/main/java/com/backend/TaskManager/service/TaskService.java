package com.backend.TaskManager.service;

import com.backend.TaskManager.dto.TaskRequest;
import com.backend.TaskManager.dto.TaskResponse;
import com.backend.TaskManager.entity.Task;
import com.backend.TaskManager.entity.User;
import com.backend.TaskManager.repository.TaskRepository;
import com.backend.TaskManager.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    public TaskService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = new Task();
        //get user from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Username not found"));

        task.setUser(user);
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        taskRepository.save(task);
//        List<Task> tasks = user.getTasks();
//        tasks.add(task);
//        user.setTasks(tasks);
//        userRepository.save(user);
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTitle(taskRequest.getTitle());
        taskResponse.setDescription(taskRequest.getDescription());
        taskResponse.setUserId(user.getId());
        return taskResponse;
    }

    public void deleteTask(Long taskId){
        taskRepository.deleteById(taskId);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        User user = userRepository.findByUsername(userDetails.getUsername())
//                .orElseThrow(() -> new RuntimeException("Username not found"));
//        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
//        List<Task> tasks = user.getTasks();
//        tasks.remove(task);
//        user.setTasks(tasks);
//        userRepository.save(user);
    }
}
