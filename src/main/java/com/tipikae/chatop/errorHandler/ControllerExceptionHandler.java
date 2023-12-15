package com.tipikae.chatop.errorHandler;

import com.tipikae.chatop.exceptions.AuthenticationException;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.user.UserAlreadyExistsException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles controller exceptions.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConverterDTOException.class)
    Error exceptionHandler(ConverterDTOException e) {
        return new Error(HttpStatus.BAD_REQUEST.value(), "Bad parameter.");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    Error exceptionHandler(UserNotFoundException e) {
        return new Error(HttpStatus.NOT_FOUND.value(), "User not found.");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    Error exceptionHandler(UserAlreadyExistsException e) {
        return new Error(HttpStatus.CONFLICT.value(), "User already exists.");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    Error exceptionHandler(ConstraintViolationException e) {
        return new Error(HttpStatus.BAD_REQUEST.value(), "Path variable not valid.");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Error exceptionHandler(MethodArgumentNotValidException e) {
        return new Error(HttpStatus.BAD_REQUEST.value(), "Parameter not valid.");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    Error exceptionHandler(AuthenticationException e) {
        return new Error(HttpStatus.BAD_REQUEST.value(), "Authentication error.");
    }
}
