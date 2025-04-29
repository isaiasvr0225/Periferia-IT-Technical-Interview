package com.microservice.task.infrastructure.repository;

import com.microservice.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @package : com.microservice.task.infrastructure.repository
 * @name : TaskRepository.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

public interface TaskRepository extends JpaRepository<Task, UUID> {

    /**
     * This method is used to update the status of a task by id
     * @param id UUID
     * @param idStatus String
     */
    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.status.id = :idStatus WHERE t.id = :id")
    void updateTaskStatusById(UUID id, Integer idStatus);
}