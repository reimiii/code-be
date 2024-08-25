package code.be.codebe.adapters.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<Map<String, String>> apiException(ResponseStatusException exception) {

    Map<String, String> response = new HashMap<>();
    response.put("code", exception.getStatusCode().toString());
    response.put("error", exception.getReason());

    return ResponseEntity
        .status(exception.getStatusCode())
        .body(response);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> constraintViolation(ConstraintViolationException violationException) {

    Map<String, String> errors = violationException.getConstraintViolations()
        .stream()
        .collect(Collectors.toMap(
            constraintViolation -> constraintViolation.getPropertyPath().toString(),
            ConstraintViolation::getMessage
        ));


    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(errors);
  }
}
