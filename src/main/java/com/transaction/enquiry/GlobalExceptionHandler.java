package com.transaction.enquiry;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author arun.balasubramanian
 * 
 * Class does the exception handling for the application, all the expected exceptions and 
 * the response(with status codes) associated with it should be defined here.
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler{
    /**
     * Method handles the MethodArgumentNotValidException from anywhere on the application and
     * responds back with a HTTP 400 status.
     * 
     * @param exception
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(MethodArgumentNotValidException exception) {
        return error(exception.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.toList()));
    }

    /**
     * Method handles the ConstraintViolationException from anywhere on the application and
     * responds back with a HTTP 400 status.
     * 
     * @param exception
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(ConstraintViolationException exception) {
        return error(exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList()));
    }
    
    /**
     * Method handles the MethodArgumentTypeMismatchException from anywhere on the application and
     * responds back with a HTTP 400 status.
     * 
     * @param exception
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(MethodArgumentTypeMismatchException exception) {
        return error(exception.getName() + " should be a number");
    }
    
    
    /**
     * Method handles the PathNotFoundException from anywhere on the application and
     * responds back with a HTTP 400 status.
     * 
     * @param exception
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handle(PathNotFoundException exception) {
    	return error("No such resource found");
    }
    
    /**
     * Method handles any other Exception apart from the above caught ones and
     * responds back with a HTTP 500 status.
     * 
     * @param exception
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handle(Exception exception) {
    	return error("Server is currently unavailable");
    }
    
    /**
     * Utility method to create the error response.
     * 
     * @param exception
     * @return
     */
    private Map<String, Object> error(Object message) {
        return Collections.singletonMap("error", message);
    }
}