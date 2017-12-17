package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.net.arh.utils.exception.DateIsBeforeExection;
import ru.net.arh.utils.exception.Exception404;
import ru.net.arh.utils.exception.ExceptionDuringSave;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception404.class)
    public void handleNotFound() {
        log.debug("404");
    }

    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    @ExceptionHandler(DateIsBeforeExection.class)
    public void editingOrCreatingWithIncorrectDate() {
        log.debug("304");
    }

    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    @ExceptionHandler(ExceptionDuringSave.class)
    public void editingOrCreatingReturedNullOrEmptyList() {
        log.debug("304");
    }

}
