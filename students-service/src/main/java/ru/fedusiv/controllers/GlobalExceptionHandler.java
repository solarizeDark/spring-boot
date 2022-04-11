package ru.fedusiv.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.fedusiv.exceptions.EntitySaveException;
import ru.fedusiv.exceptions.NoEntityException;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntitySaveException.class, NoEntityException.class})
    public ResponseEntity<String> handle(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleList(ConstraintViolationException exception) {
        StringBuilder response = new StringBuilder();
        exception.getConstraintViolations().
                forEach(violation -> {
                    response.append(violation.getMessage()).append(": ");
                    response.append(violation.getInvalidValue()).append("\n");
                });
        return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
    }

}
