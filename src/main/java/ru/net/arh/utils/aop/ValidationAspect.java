package ru.net.arh.utils.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ru.net.arh.utils.aop.annotation.*;
import ru.net.arh.utils.exception.DateIsBeforeExection;
import ru.net.arh.utils.exception.DuplicateValueForUniqueIndexException;
import ru.net.arh.utils.exception.Exception404;
import ru.net.arh.utils.exception.ExceptionDuringSave;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Aspect
@Component
public class ValidationAspect {

    @Pointcut("@annotation(ru.net.arh.utils.aop.annotation.Throw404IfNullResult)")
    private void throw404IfNullResult() {
    }

    @Pointcut("@annotation(ru.net.arh.utils.aop.annotation.Throw404IfFalseResult)")
    private void throw404IfFalseResult() {
    }

    @Pointcut("@annotation(ru.net.arh.utils.aop.annotation.ThrowDuplicateValueForUniqueIndexIfNullResult)")
    private void throwDuplicateValueForUniqueIndexIfNullResult() {
    }

    @Pointcut("@annotation(ru.net.arh.utils.aop.annotation.ThrowIfLocalDateParamBeforeToday)")
    private void throwIfLocalDateParamBeforeToday() {
    }

    @Pointcut("@annotation(ru.net.arh.utils.aop.annotation.ThrowIfException)")
    private void throwIfException() {
    }

    @Pointcut("@annotation(ru.net.arh.utils.aop.annotation.ThrowIfEmptyListReturned)")
    private void throwIfEmptyListReturned() {
    }

    @AfterThrowing(value = "throwIfException() && @annotation(annotation)", throwing = "exception")
    public void checkResult1(ThrowIfException annotation, Throwable exception) {
        throw new ExceptionDuringSave(annotation.message(), exception);
    }

    @Before(value = "throwIfLocalDateParamBeforeToday() && @annotation(annotation) && args(date,..)")
    public void checkDate(LocalDate date, ThrowIfLocalDateParamBeforeToday annotation) {
        log.debug("checking date");
        if (date.isBefore(LocalDate.now())) {
            log.debug("throwing DateIsBeforeExection");
            throw new DateIsBeforeExection(annotation.message());
        }
    }

    @AfterReturning(value = "throwIfEmptyListReturned() && @annotation(annotation)", returning = "result")
    public void checkResult2(List result, Throw404IfNullResult annotation) {
        log.debug("checking result for null");
        if (result.isEmpty()) {
            log.debug("throwing Exception404");
            throw new Exception404(annotation.message());
        }
    }

    @AfterReturning(value = "throw404IfNullResult() && @annotation(annotation)", returning = "result")
    public void checkResult1(Object result, Throw404IfNullResult annotation) {
        log.debug("checking result for null");
        if (result == null) {
            log.debug("throwing Exception404");
            throw new Exception404(annotation.message());
        }
    }

    @AfterReturning(value = "throw404IfFalseResult() && @annotation(annotation)", returning = "result")
    public void checkResult2(boolean result, Throw404IfFalseResult annotation) {
        log.debug("checking result for false");
        if (result == false) {
            log.debug("throwing Exception404");
            throw new Exception404(annotation.message());
        }
    }

    @AfterReturning(value = "throwDuplicateValueForUniqueIndexIfNullResult() && @annotation(annotation)", returning = "result")
    public void checkResult3(Object result, ThrowDuplicateValueForUniqueIndexIfNullResult annotation) {
        log.debug("checking result for null");
        if (result == null) {
            log.debug("throwing DuplicateValueForUniqueIndexException");
            throw new DuplicateValueForUniqueIndexException(annotation.message());
        }
    }
}
