package com.microservice.task.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.UUID;

/**
 * @package : com.microservice.task.domain
 * @name : Task.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "tasks")
public @Entity class Task {
    @Id
    @Column(name = "id")
    private UUID id;

    @NotBlank(message = "El campo title no puede estar vacío")
    private String title;

    private String description;
}

