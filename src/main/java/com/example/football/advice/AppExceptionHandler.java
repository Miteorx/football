package com.example.football.advice;

import com.example.football.exceptions.TeamDoesntExist;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handler(MethodArgumentNotValidException ex) {
    Map<String, String> errorMap = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> {
      errorMap.put(error.getField(), error.getDefaultMessage());
    });
    return errorMap;
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(TeamDoesntExist.class)
  public Map<String, String> businessExceptions(TeamDoesntExist ex) {
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("Error message", ex.getMessage());
    return errorMap;
  }
}
