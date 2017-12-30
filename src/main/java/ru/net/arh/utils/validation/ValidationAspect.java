package ru.net.arh.utils.validation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ru.net.arh.utils.validation.annotation.CheckForException;
import ru.net.arh.utils.validation.annotation.CheckForFalseResult;
import ru.net.arh.utils.validation.annotation.CheckForLocalDateParamBeforeToday;
import ru.net.arh.utils.validation.annotation.CheckForNullResult;
import ru.net.arh.utils.validation.exception.ValidationException;

import java.time.LocalDate;

@Slf4j
@Aspect
@Component
public class ValidationAspect {

    @Pointcut("@annotation(ru.net.arh.utils.validation.annotation.CheckForNullResult)")
    private void checkForNullResult() {
    }

    @Pointcut("@annotation(ru.net.arh.utils.validation.annotation.CheckForFalseResult)")
    private void checkForFalseResutl() {
    }

    @Pointcut("@annotation(ru.net.arh.utils.validation.annotation.CheckForLocalDateParamBeforeToday)")
    private void checkForLocalDateParamBeforeToday() {
    }

    @Pointcut("@annotation(ru.net.arh.utils.validation.annotation.CheckForException)")
    private void checkForException() {
    }

    @AfterThrowing(value = "checkForException() && @annotation(annotation)", throwing = "exception")
    public void checkResult1(CheckForException annotation, Throwable exception) throws IllegalAccessException, InstantiationException {
        throw new ValidationException(annotation.message(), exception, annotation.status());
    }

    @Before(value = "checkForLocalDateParamBeforeToday() && @annotation(annotation) && args(date,..)")
    public void checkDate(LocalDate date, CheckForLocalDateParamBeforeToday annotation) {
        if (date.isBefore(LocalDate.now())) {
            throw new ValidationException(annotation.message(), annotation.status());
        }
    }

    @AfterReturning(value = "checkForNullResult() && @annotation(annotation)", returning = "result")
    public void checkResult1(Object result, CheckForNullResult annotation) {
        if (result == null) {
            throw new ValidationException(annotation.message(), annotation.status());
        }
    }

    @AfterReturning(value = "checkForFalseResutl() && @annotation(annotation)", returning = "result")
    public void checkResult2(boolean result, CheckForFalseResult annotation) {
        if (result == false) {
            throw new ValidationException(annotation.message(), annotation.status());
        }
    }

}
