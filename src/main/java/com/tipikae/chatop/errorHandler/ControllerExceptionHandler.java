package com.tipikae.chatop.errorHandler;

import com.tipikae.chatop.exceptions.AuthenticationException;
import com.tipikae.chatop.exceptions.ConverterDTOException;
import com.tipikae.chatop.exceptions.storage.FileNotFoundException;
import com.tipikae.chatop.exceptions.storage.StorageException;
import com.tipikae.chatop.exceptions.user.UserAlreadyExistsException;
import com.tipikae.chatop.exceptions.user.UserNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
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
@Hidden
public class ControllerExceptionHandler {

    /**
     * ConverterDTOException handler.
     * @param e ConverterDTOException object.
     * @return Error 400
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConverterDTOException.class)
    Error exceptionHandler(ConverterDTOException e) {
        return new Error(HttpStatus.BAD_REQUEST.value(), "Bad parameter.");
    }

    /**
     * ConstraintViolationException handler.
     * @param e ConstraintViolationException object.
     * @return Error 400
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    Error exceptionHandler(ConstraintViolationException e) {
        return new Error(HttpStatus.BAD_REQUEST.value(), "Path variable not valid.");
    }

    /**
     * MethodArgumentNotValidException handler.
     * @param e MethodArgumentNotValidException object.
     * @return Error 400
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Error exceptionHandler(MethodArgumentNotValidException e) {
        return new Error(HttpStatus.BAD_REQUEST.value(), "Parameter not valid.");
    }

    /**
     * StorageException handler.
     * @param e StorageException object.
     * @return Error 400
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(StorageException.class)
    Error exceptionHandler(StorageException e) {
        return new Error(HttpStatus.BAD_REQUEST.value(), "Storage error.");
    }

    /**
     * AuthenticationException handler.
     * @param e AuthenticationException object.
     * @return Error 401
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    Error exceptionHandler(AuthenticationException e) {
        return new Error(HttpStatus.UNAUTHORIZED.value(), "Authentication error.");
    }

    /**
     * UserNotFoundException handler.
     * @param e UserNotFoundException object.
     * @return Error 404
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    Error exceptionHandler(UserNotFoundException e) {
        return new Error(HttpStatus.NOT_FOUND.value(), "User not found.");
    }

    /**
     * FileNotFoundException handler.
     * @param e FileNotFoundException object.
     * @return Error 404
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException.class)
    Error exceptionHandler(FileNotFoundException e) {
        return new Error(HttpStatus.NOT_FOUND.value(), "File not found.");
    }

    /**
     * UserAlreadyExistsException handler.
     * @param e UserAlreadyExistsException object.
     * @return Error 409
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistsException.class)
    Error exceptionHandler(UserAlreadyExistsException e) {
        return new Error(HttpStatus.CONFLICT.value(), "User already exists.");
    }
}
