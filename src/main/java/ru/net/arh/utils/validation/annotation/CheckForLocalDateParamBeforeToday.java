package ru.net.arh.utils.validation.annotation;

import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckForLocalDateParamBeforeToday {
    String message() default "Date in parameter cannot be before 'today'";

    HttpStatus status();
}
