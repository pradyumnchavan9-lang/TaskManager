package com.backend.TaskManager.entity;

import com.backend.TaskManager.dto.TaskResponse;
import com.backend.TaskManager.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Role role;
    @OneToMany
    private List<Task> tasks =  new ArrayList<Task>();
}
