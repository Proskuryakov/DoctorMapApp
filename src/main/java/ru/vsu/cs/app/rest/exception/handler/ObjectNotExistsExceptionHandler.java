package ru.vsu.cs.app.rest.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vsu.cs.app.rest.exception.ObjectNotExistsException;

@ControllerAdvice
public class ObjectNotExistsExceptionHandler {

    @ExceptionHandler(ObjectNotExistsException.class)
    ResponseEntity<?> handleNotFoundException(ObjectNotExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
