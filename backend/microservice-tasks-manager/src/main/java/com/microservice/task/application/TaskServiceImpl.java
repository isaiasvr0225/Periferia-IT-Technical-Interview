package com.microservice.task.application;

import com.microservice.task.domain.Task;
import com.microservice.task.domain.exceptions.TaskException;
import com.microservice.task.infrastructure.dto.*;
import com.microservice.task.infrastructure.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @package : com.microservice.task.application
 * @name : TaskServiceImpl.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@Slf4j
@RequiredArgsConstructor
public @Service class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    @Override
    public Page<TaskResponseDTO> findAll(Pageable pageable) {
        Page<Task> taskPage = this.taskRepository.findAll(pageable);

        if (taskPage.isEmpty()) {
            throw new TaskException("No hay Tareas registradas", HttpStatus.NOT_FOUND);
        }

        log.info("Lista de tareas: {}", taskPage);

        return taskPage.map(product -> new TaskResponseDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription()
        ));
    }


    @Transactional
    @Override
    public HttpStatus save(JsonApiRequestDTO<SaveNewTaskDTO> saveNewProductDTO) {
        Task task = Task.builder()
                .id(UUID.randomUUID())
                .title(saveNewProductDTO.data().attributes().title())
                .description(saveNewProductDTO.data().attributes().description())
                .build();

        this.taskRepository.save(task);

        return HttpStatus.CREATED;
    }

    @Override
    public JsonApiResponseDTO<TaskResponseDTO> findById(UUID id) {
        Task task = this.taskRepository.findById(id)
                .orElseThrow(() -> new TaskException("Task not found", HttpStatus.NOT_FOUND));

        TaskResponseDTO attributes = TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .build();
        JsonApiData<TaskResponseDTO> data = JsonApiData.<TaskResponseDTO>builder()
                .type("tasks")
                .id(String.valueOf(task.getId()))
                .attributes(attributes)
                .build();
        return JsonApiResponseDTO.<TaskResponseDTO>builder()
                .data(data)
                .build();
    }

    @Override
    public HttpStatus updateById(UUID id, JsonApiRequestDTO<SaveNewTaskDTO> saveNewProductDTO) {
        Task task = this.taskRepository.findById(id)
                .orElseThrow(() -> new TaskException("Task not found", HttpStatus.NOT_FOUND));

        task.setTitle(saveNewProductDTO.data().attributes().title());
        task.setDescription(saveNewProductDTO.data().attributes().description());

        this.taskRepository.save(task);
        return HttpStatus.OK;
    }

    @Override
    public HttpStatus deleteById(UUID id) {
        Task task = this.taskRepository.findById(id)
                .orElseThrow(() -> new TaskException("Task not found", HttpStatus.NOT_FOUND));

        this.taskRepository.delete(task);
        return HttpStatus.OK;
    }

}
