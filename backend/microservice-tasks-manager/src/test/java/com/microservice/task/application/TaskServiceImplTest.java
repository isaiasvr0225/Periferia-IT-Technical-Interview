package com.microservice.task.application;

import com.microservice.task.domain.Task;
import com.microservice.task.domain.exceptions.TaskException;
import com.microservice.task.infrastructure.dto.*;
import com.microservice.task.infrastructure.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnPageOfTasks() {
        Task task = Task.builder()
                .id(UUID.randomUUID())
                .title("Test Task")
                .description("Test Description")
                .build();

        Page<Task> page = new PageImpl<>(List.of(task));
        when(taskRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<TaskResponseDTO> result = taskService.findAll(PageRequest.of(0, 10));

        assertFalse(result.isEmpty());
        assertEquals("Test Task", result.getContent().get(0).title());
        verify(taskRepository).findAll(any(Pageable.class));
    }

    @Test
    void findAll_ShouldThrowException_WhenNoTasks() {
        when(taskRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());

        TaskException exception = assertThrows(TaskException.class, () ->
                taskService.findAll(PageRequest.of(0, 10)));

        assertEquals("No hay Tareas registradas", exception.getMessage());
    }

    @Test
    void save_ShouldCreateANewTask() {

        JsonApiData<SaveNewTaskDTO> jsonApiData = JsonApiData.<SaveNewTaskDTO>builder()
                .attributes(new SaveNewTaskDTO("Test Task", "Test Description"))
                .build();

        JsonApiRequestDTO<SaveNewTaskDTO> dto = JsonApiRequestDTO.<SaveNewTaskDTO>builder()
                .data(jsonApiData)
                .build();

        var status = taskService.save(dto);

        assertEquals(CREATED, status);
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void findById_ShouldReturnTask_WhenExists() {
        UUID id = UUID.randomUUID();
        Task task = Task.builder()
                .id(id)
                .title("Task")
                .description("Description")
                .build();

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        var response = taskService.findById(id);

        assertNotNull(response);
        assertEquals("Task", response.data().attributes().title());
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        UUID id = UUID.randomUUID();
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TaskException.class, () -> taskService.findById(id));
    }

    @Test
    void updateById_ShouldUpdateProduct_WhenFound() {
        UUID id = UUID.randomUUID();
        Task task = Task.builder()
                .id(id)
                .title("Old Task")
                .description("Old description")
                .build();

        JsonApiData<SaveNewTaskDTO> jsonApiData = JsonApiData.<SaveNewTaskDTO>builder()
                .attributes(new SaveNewTaskDTO("Updated Task", "Updated Description"))
                .build();

        JsonApiRequestDTO<SaveNewTaskDTO> dto = JsonApiRequestDTO.<SaveNewTaskDTO>builder()
                .data(jsonApiData)
                .build();

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        var result = taskService.updateById(id, dto);

        assertEquals(OK, result);
        verify(taskRepository).save(task);
        assertEquals("Updated Task", task.getTitle());
    }

    @Test
    void updateById_ShouldThrowException_WhenNotFound() {
        UUID id = UUID.randomUUID();

        JsonApiData<SaveNewTaskDTO> jsonApiData = JsonApiData.<SaveNewTaskDTO>builder()
                .attributes(new SaveNewTaskDTO("Updated Task", "Updated Description"))
                .build();

        JsonApiRequestDTO<SaveNewTaskDTO> dto = JsonApiRequestDTO.<SaveNewTaskDTO>builder()
                .data(jsonApiData)
                .build();

        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TaskException.class, () -> taskService.updateById(id, dto));
    }

    @Test
    void deleteById_ShouldDeleteProduct_WhenFound() {
        UUID id = UUID.randomUUID();
        Task task = Task.builder().id(id).build();

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        var result = taskService.deleteById(id);

        assertEquals(OK, result);
        verify(taskRepository).delete(task);
    }

    @Test
    void deleteById_ShouldThrowException_WhenNotFound() {
        UUID id = UUID.randomUUID();
        when(taskRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TaskException.class, () -> taskService.deleteById(id));
    }
}
