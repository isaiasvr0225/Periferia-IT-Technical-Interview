package com.microservice.task.application;

import com.microservice.task.infrastructure.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.UUID;

/**
 * @package : com.microservice.task.application
 * @name : TaskService.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

public interface TaskService {

    /**
     * This method is used to find all tasks using pagination
     * @param pageable Pageable
     * @return Page<TaskResponseDTO>
     */
    Page<TaskResponseDTO> findAll(Pageable pageable);

    /**
     * This method is used to save a new task
     * @param saveNewTaskDTO JsonApiRequestDTO<SaveNewTaskDTO>
     * @return HttpStatus
     */
    HttpStatus save(JsonApiRequestDTO<SaveNewTaskDTO> saveNewTaskDTO);

    /**
     * This method is used to find a task by id
     * @param id UUID
     * @return TaskResponseDTO
     */
    JsonApiResponseDTO<TaskResponseDTO> findById(UUID id);

    /**
     * This method is used to update a task by id
     * @param id id
     * @param updateTaskDTO UpdateTaskDTO
     * @return HttpStatus
     */
    HttpStatus updateById(UUID id, JsonApiRequestDTO<UpdateTaskDTO> updateTaskDTO);

    /**
     * This method is used to update a task status by id
     * @param id id
     * @param newStatusName String
     * @return HttpStatus
     */
    HttpStatus updateTaskStatusById(UUID id, String newStatusName);

    /**
     * This method is used to delete a task by id
     * @param id id
     * @return HttpStatus
     */
    HttpStatus deleteById(UUID id);
}
