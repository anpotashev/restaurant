package ru.net.arh.utils.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Exception404 extends RuntimeException {
    public Exception404(String message) {
        super(message);
    }
}
