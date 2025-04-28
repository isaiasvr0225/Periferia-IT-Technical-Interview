package com.microservice.task.infrastructure.dto;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @package : com.microservice.task.infrastructure.dto
 * @name : SaveNewTaskDTO.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * DTO for {@link com.microservice.task.domain.Task}
 */
public record SaveNewTaskDTO(String title, String description) implements Serializable {
}