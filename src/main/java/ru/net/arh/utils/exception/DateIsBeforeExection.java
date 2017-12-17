package ru.net.arh.utils.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateIsBeforeExection extends RuntimeException {
    public DateIsBeforeExection(String message) {
        super(message);
    }

}
