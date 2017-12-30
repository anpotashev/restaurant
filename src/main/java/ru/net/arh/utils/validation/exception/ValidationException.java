package ru.net.arh.utils.validation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ValidationException extends RuntimeException {

    @Getter
    HttpStatus status;

    public ValidationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ValidationException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }
}
