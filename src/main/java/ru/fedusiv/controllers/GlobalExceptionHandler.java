package ru.fedusiv.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.fedusiv.exceptions.NoEntityException;
import ru.fedusiv.exceptions.EntitySaveException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntitySaveException.class, NoEntityException.class})
    public ResponseEntity<String> handle(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
