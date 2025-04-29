package com.microservice.task.domain.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @package : com.microservice.task.domain.exceptions
 * @name : StatusException.java
 * @date : 2025-04-27
 * @author : Isaias Villarreal
 * @version : 1.0.0
 */

@Data
public class StatusException extends RuntimeException {

    private HttpStatus httpStatus;

    public StatusException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
