package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.net.arh.utils.validation.exception.ValidationException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler({ValidationException.class})
    public ResponseEntity handleValidationException(ValidationException exception) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("exceptionCause", exception.getMessage());
        headers.add("parentException", exception.getCause() != null ? exception.getCause().toString() : "");
        ResponseEntity responseEntity = new ResponseEntity(headers, exception.getStatus());
        return responseEntity;
    }

//    @ExceptionHandler({AccessDeniedException.class})
//    public ResponseEntity handleAccessDenied(AccessDeniedException exception) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("exceptionCause", exception.getMessage());
//        ResponseEntity responseEntity = new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
//        return responseEntity;
//    }

}
