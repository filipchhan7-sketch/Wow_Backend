package com.wow.WowPotato.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex){
      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getFieldErrors()
              .forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));
      return ResponseEntity.badRequest().body(errors);
    }
   @ExceptionHandler(NoSuchElementException.class)
   public ResponseEntity<Map<String, String>> handleNotFound(NoSuchElementException ex){
     return ResponseEntity.status(HttpStatus.NOT_FOUND)
             .body(Map.of("error", ex.getMessage()));
   }

}

