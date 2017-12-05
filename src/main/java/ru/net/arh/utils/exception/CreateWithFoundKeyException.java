package ru.net.arh.utils.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateWithFoundKeyException extends RuntimeException {
    public CreateWithFoundKeyException(String message) {
        super(message);
    }
}
