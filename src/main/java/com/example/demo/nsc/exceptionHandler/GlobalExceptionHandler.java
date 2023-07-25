package com.example.demo.nsc.exceptionHandler;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), List.of(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<?>  handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request)
    {
        ErrorDetails errorDetails=new ErrorDetails();
        errorDetails.setMessages(ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setDetails(request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public final ResponseEntity<ErrorDetails>  handleEmailAlreadyExistsException(Exception ex, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), List.of(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<ErrorDetails>  handleRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), List.of(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public final ResponseEntity<ErrorDetails>  handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request)
    {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), List.of(ex.getMessage()), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
