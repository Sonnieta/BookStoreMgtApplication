package com.Backend.BookStoreMgt.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Handle Book Not Found Exception
  @ExceptionHandler(BookNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Map<String, Object>> handleBookNotFoundException(BookNotFoundException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.NOT_FOUND.value());
    response.put("error", "Book Not Found");
    response.put("message", ex.getMessage());

    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  // Handle Other Exceptions
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.put("error", "Internal Server Error");
    response.put("message", ex.getMessage());

    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}