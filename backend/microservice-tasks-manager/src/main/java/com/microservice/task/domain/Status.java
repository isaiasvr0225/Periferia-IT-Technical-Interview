package com.microservice.task.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "status")
public @Entity class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    public Status(String name) {
        this.name = name;
    }
}
