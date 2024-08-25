package code.be.codebe.adapters.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

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
}
