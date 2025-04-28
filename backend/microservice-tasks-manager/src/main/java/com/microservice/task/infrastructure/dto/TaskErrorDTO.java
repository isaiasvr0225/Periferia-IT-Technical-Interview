package com.microservice.task.infrastructure.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * This is a DTO class for Task error handling
 * @package : com.microservice.task.infrastructure.dto
 * @name : TaskErrorDto.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
@Builder
public record TaskErrorDTO(
        HttpStatus statusCode,
        String message
) implements Serializable {}
