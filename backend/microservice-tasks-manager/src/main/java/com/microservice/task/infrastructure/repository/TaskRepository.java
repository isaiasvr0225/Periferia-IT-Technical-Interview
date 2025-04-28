package com.microservice.task.infrastructure.repository;

import com.microservice.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @package : com.microservice.task.infrastructure.repository
 * @name : TaskRepository.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

public interface TaskRepository extends JpaRepository<Task, UUID> {
}