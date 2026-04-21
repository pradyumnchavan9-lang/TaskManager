package com.backend.TaskManager.dto;

import com.backend.TaskManager.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private List<Long> taskIds;
    private Role role;

}
