package ru.net.arh.utils.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DuplicateValueForUniqueIndexException extends RuntimeException {
    public DuplicateValueForUniqueIndexException(String message) {
        super(message);
    }
}
