package com.microservice.task.presentation.controller_advice;
import com.microservice.task.domain.exceptions.TaskException;
import com.microservice.task.infrastructure.dto.TaskErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @package : com.microservice.task.presentation.controller_advice
 * @name : TaskControllerAdvice.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */
@RestControllerAdvice
public class TaskControllerAdvice {

    /**
     * This method is used to handle TaskException
     * @param taskException taskException
     * @return ResponseEntity<TaskErrorDto>
     */
    @ExceptionHandler(value = TaskException.class)
    public ResponseEntity<TaskErrorDTO> handleProductException(TaskException taskException) {
        return ResponseEntity.status(taskException.getHttpStatus()).body(TaskErrorDTO.builder()
                .statusCode(taskException.getHttpStatus())
                .message(taskException.getMessage())
                .build());
    }
}
