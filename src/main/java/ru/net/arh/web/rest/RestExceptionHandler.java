package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.net.arh.utils.validation.exception.ValidationException;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler({ValidationException.class})
    public ResponseEntity handleNotFound(ValidationException exception) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("exceptionCause", exception.getMessage());
        headers.add("parentException", exception.getCause() != null ? exception.getCause().toString() : "");
        ResponseEntity responseEntity = new ResponseEntity(headers, exception.getStatus());
        return responseEntity;
    }

}
