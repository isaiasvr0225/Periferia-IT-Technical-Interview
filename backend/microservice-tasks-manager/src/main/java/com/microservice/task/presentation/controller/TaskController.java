package com.microservice.task.presentation.controller;

import com.microservice.task.application.TaskService;
import com.microservice.task.infrastructure.dto.JsonApiRequestDTO;
import com.microservice.task.infrastructure.dto.JsonApiResponseDTO;
import com.microservice.task.infrastructure.dto.SaveNewTaskDTO;
import com.microservice.task.infrastructure.dto.TaskResponseDTO;
import com.microservice.task.infrastructure.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

/**
 * @package : com.microservice.task.presentation.controller
 * @name : TaskController.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public @RestController class TaskController {

    private final TaskRepository taskRepository;

    private final TaskService taskService;

    //@PreAuthorize("permitAll()")
    @GetMapping("/count")
    public ResponseEntity<?> countTasks() {
        return  ResponseEntity.ok(this.taskRepository.count());
    }

    //@PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<Page<TaskResponseDTO>> findAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.taskService.findAll(pageable));
    }


    //@PreAuthorize("permitAll()")
    //@PreAuthorize("hasAnyRole('ADMIN', 'CARRIER')")
    @GetMapping("/{taskId}")
    public ResponseEntity<JsonApiResponseDTO<TaskResponseDTO>> findById(@PathVariable(name = "taskId") UUID taskId) {
        return ResponseEntity.ok(this.taskService.findById(taskId));
    }


    //@PreAuthorize("permitAll()")
    @PostMapping
    public HttpStatus save(@RequestBody JsonApiRequestDTO<SaveNewTaskDTO> saveNewTaskDTO) {

        return this.taskService.save(saveNewTaskDTO);
    }


    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{taskId}")
    public HttpStatus update(@PathVariable(name = "taskId") UUID taskId, @RequestBody JsonApiRequestDTO<SaveNewTaskDTO> newProductDTO) {
        return this.taskService.updateById(taskId, newProductDTO);
    }

    //@PreAuthorize("hasAnyRole('ADMIN', 'CARRIER')")
    @DeleteMapping("/{taskId}")
    public HttpStatus delete(@PathVariable(name = "taskId") UUID taskId) {

        return this.taskService.deleteById(taskId);
    }


}
