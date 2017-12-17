package ru.net.arh.utils.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThrowIfLocalDateParamBeforeToday {
    String message() default "Date in parameter cannot be before 'today'";
}
