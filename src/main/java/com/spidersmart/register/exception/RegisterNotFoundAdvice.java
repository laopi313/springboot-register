package com.spidersmart.register.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
class RegisterNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(RegisterNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String RegisterNotFoundHandler(RegisterNotFoundException ex) {
    return ex.getMessage();
  }
}