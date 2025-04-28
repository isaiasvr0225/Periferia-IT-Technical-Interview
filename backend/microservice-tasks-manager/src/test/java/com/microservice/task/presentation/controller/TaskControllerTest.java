package com.microservice.task.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.task.application.TaskService;
import com.microservice.task.infrastructure.dto.*;
import com.microservice.task.infrastructure.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/v1/tasks/count - Should return count")
    void countTasks() throws Exception {
        Mockito.when(taskRepository.count()).thenReturn(5L);

        mockMvc.perform(get("/api/v1/tasks/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    @DisplayName("GET /api/v1/tasks - Should return list of tasks")
    void findAll() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Mockito.when(taskService.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/v1/tasks/{taskId} - Should return task by ID")
    void findById() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(get("/api/v1/tasks/{taskId}", id))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST /api/v1/tasks - Should save a task")
    void save() throws Exception {
        JsonApiData<SaveNewTaskDTO> jsonApiData = JsonApiData.<SaveNewTaskDTO>builder()
                .id(String.valueOf(UUID.randomUUID()))
                .type("tasks")
                .attributes(new SaveNewTaskDTO("Test Task", "Test Description"))
                .build();

        JsonApiRequestDTO<SaveNewTaskDTO> dto = JsonApiRequestDTO.<SaveNewTaskDTO>builder()
                .data(jsonApiData)
                .build();

        Mockito.when(taskService.save(any(JsonApiRequestDTO.class))).thenReturn(HttpStatus.CREATED);

        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("PUT /api/v1/tasks/{taskId} - Should update a task")
    void update() throws Exception {
        UUID id = UUID.randomUUID();
        JsonApiData<SaveNewTaskDTO> jsonApiData = JsonApiData.<SaveNewTaskDTO>builder()
                .attributes(new SaveNewTaskDTO("Test Task", "Test Description"))
                .build();

        JsonApiRequestDTO<SaveNewTaskDTO> dto = JsonApiRequestDTO.<SaveNewTaskDTO>builder()
                .data(jsonApiData)
                .build();

        Mockito.when(taskService.updateById(any(UUID.class), any(JsonApiRequestDTO.class))).thenReturn(HttpStatus.OK);

        mockMvc.perform(put("/api/v1/tasks/{taskId}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /api/v1/tasks/{taskId} - Should delete a task")
    void deleteById() throws Exception {
        UUID id = UUID.randomUUID();

        Mockito.when(taskService.deleteById(id)).thenReturn(HttpStatus.OK);

        mockMvc.perform(delete("/api/v1/tasks/{taskId}", id))
                .andExpect(status().isOk());
    }
}
