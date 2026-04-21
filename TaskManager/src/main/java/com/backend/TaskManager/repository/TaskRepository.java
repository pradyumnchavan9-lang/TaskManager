package com.backend.TaskManager.repository;

import com.backend.TaskManager.entity.Task;
import com.backend.TaskManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
