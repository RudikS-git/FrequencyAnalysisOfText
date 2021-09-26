package com.example.webfrequenceanalysisoftext.exceptionhandlers;

import com.example.webfrequenceanalysisoftext.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.webfrequenceanalysisoftext.utils.Response;

@ControllerAdvice
public class CommonAdvice {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Response> handleException(BusinessException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
