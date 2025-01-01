package com.devinfusion.eventia.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.devinfusion.eventia.dto.ErrorDTO;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyExists(UserAlreadyExists ex) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.CONFLICT.value(), ex.getMessage(), "Conflict: Duplicate resource");
        return new ResponseEntity<>(errorDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorDTO> handleUserNotFound(UserNotFound ex) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), "Resource not found");
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGeneralException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred", ex.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
