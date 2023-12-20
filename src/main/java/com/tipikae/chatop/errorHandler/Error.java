package com.tipikae.chatop.errorHandler;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Error POJO returned by ControllerAdvice.
 */
@Data
public class Error implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private Date timestamp;

    /**
     * Constructor.
     * @param code Error code
     * @param message String
     */
    public Error(int code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = new Date();
    }
}
