package ru.net.arh.utils.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionDuringSave extends RuntimeException {
    public ExceptionDuringSave(String message, Throwable cause) {
        super(message, cause);
    }
}
