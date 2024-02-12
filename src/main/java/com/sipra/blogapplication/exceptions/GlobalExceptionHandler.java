package com.sipra.blogapplication.exceptions;

import com.sipra.blogapplication.payLoads.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception){
        String message = exception.getMessage();
        ResponseDto responseDto = new ResponseDto(false,message);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            response.put(fieldName,message);
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseDto> handleIoException(IOException ioException){
        String message =ioException.getMessage();
        return new ResponseEntity<>(new ResponseDto(false,message),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ResponseDto> handleFileNotFoundException(IOException fileNotFoundException){
        String message =fileNotFoundException.getMessage();
        return new ResponseEntity<>(new ResponseDto(false,message),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseDto>handleApiException(ApiException apiException){
        String message = apiException.getMessage();
        return new ResponseEntity<>(new ResponseDto(false,message),HttpStatus.BAD_REQUEST);
    }

}

