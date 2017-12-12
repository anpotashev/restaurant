package ru.net.arh.utils.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.net.arh.model.mapped.AbstractBaseEntity;
import ru.net.arh.utils.exception.CreateWithFoundKeyException;
import ru.net.arh.utils.exception.NotFoundException;

@Aspect
@Component
public class ValidationAspect {

    private static final String NOT_FOUND_MESSAGE = "Not found with id=";

    private static void check(Object checkValue, int id) {
        check(checkValue != null, id);
    }

    private static void check(boolean isOk, int id) {
        if (!isOk) {
            throw new NotFoundException(NOT_FOUND_MESSAGE + id);
        }
    }

    @Pointcut("@annotation(ru.net.arh.utils.aop.annotation.NeedValidateReturnValueForNullOrFalse)")
    private void methodsWithAnnotationNeedValidateReturnValueForNullOrFalse() {
    }

    @Pointcut("@annotation(ru.net.arh.utils.aop.annotation.NeedValidateReturnValueForNullOnCreate)")
    private void methodsWithAnnotationNeedValidateReturnValueForNullOnCreate() {
    }

    @Pointcut("execution(boolean *(..))")
    private void returnBoolean() {
    }

    @Pointcut("execution(ru.net.arh.model.mapped.AbstractBaseEntity *(..))")
    private void returnEntity() {
    }

    @Pointcut("execution(* *(int))")
    private void acceptInt() {
    }

    @Pointcut("execution(* *(ru.net.arh.model.mapped.AbstractBaseEntity*))")
    private void acceptEntity() {
    }

    @AfterReturning(value = "methodsWithAnnotationNeedValidateReturnValueForNullOnCreate())", returning = "result")
    public void checkCreateResult(JoinPoint jp, Object result) {
        if (result == null) {
            throw new CreateWithFoundKeyException("Creating with existed primary key");
        }
    }

    @AfterReturning(value = "acceptInt() && returnEntity() && methodsWithAnnotationNeedValidateReturnValueForNullOrFalse() && args(id)", returning = "result")
    public void checkResultForNull(JoinPoint jp, Object result, int id) {
        check(result, id);
    }

    @AfterReturning(value = "acceptEntity() && returnEntity() && methodsWithAnnotationNeedValidateReturnValueForNullOrFalse() && args(entity)", returning = "result")
    public void checkResultForNullWithEntityParameter(JoinPoint jp, Object result, AbstractBaseEntity entity) {
        check(result, entity.getId());
    }

    @AfterReturning(value = "acceptInt() && returnBoolean() && methodsWithAnnotationNeedValidateReturnValueForNullOrFalse() && args(id)", returning = "result")
    public void checkResultForFalse(JoinPoint jp, boolean result, int id) {
        check(result, id);
    }

}
