package com.backend.TaskManager.dto;

import com.backend.TaskManager.enums.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private Long id;
    private String username;
    private Role role;
}
