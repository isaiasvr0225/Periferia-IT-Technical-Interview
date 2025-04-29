package com.microservice.task.infrastructure.dto;

import java.io.Serializable;


/**
 * @package : com.microservice.task.infrastructure.dto
 * @name : SaveNewTaskDTO.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * DTO for {@link com.microservice.task.domain.Task}
 */
public record UpdateTaskDTO(String newTitle, String newDescription, String newStatus) implements Serializable {
}