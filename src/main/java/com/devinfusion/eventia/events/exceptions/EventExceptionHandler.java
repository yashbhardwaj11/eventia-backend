package com.devinfusion.eventia.events.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.devinfusion.eventia.dto.ErrorDTO;


@ControllerAdvice
public class EventExceptionHandler {

    @ExceptionHandler(EventNotFound.class)
    public ResponseEntity<ErrorDTO> handleEventNotExists(EventNotFound ex) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage(), "Resource not found");
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

}
