package com.tipikae.chatop.errorHandler;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class Error implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private Date timestamp;

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = new Date();
    }
}