package com.microservice.task.infrastructure.dto;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @package : com.microservice.task.infrastructure.dto
 * @name : TaskResponseDTO.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * DTO for {@link com.microservice.task.domain.Task}
 */
@Builder
public record TaskResponseDTO(UUID id, String title, String description) implements Serializable {
}