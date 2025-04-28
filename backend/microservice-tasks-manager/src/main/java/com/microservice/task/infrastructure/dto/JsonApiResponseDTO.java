package com.microservice.task.infrastructure.dto;

import lombok.Builder;

/**
 * @package : com.microservice.task.infrastructure.dto
 * @name : JsonApiResponseDTO.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@Builder
public record JsonApiResponseDTO<T>(JsonApiData<T> data) {}
