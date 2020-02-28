package test.elasticsearch_rest.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.*;
import test.elasticsearch_rest.demo.exceptions.AppException;
import test.elasticsearch_rest.demo.exceptions.enums.AppExceptions;
import test.elasticsearch_rest.demo.model.User;

@Aspect
@Component
public class IdEqualsSentObjectIdAspect {

    @Before("@annotation(test.elasticsearch_rest.demo.aspects.annotations.IdEqualsSentObjectIdAdvice) && args(@PathVariable(value=\"id\") id, @RequestBody transientObject)")
    public void idUpdateObjectValidation(JoinPoint joinPoint, String id, User transientObject) {
        if( !id.equals(transientObject.getUsername()) )
            throw new AppException(AppExceptions.ID_OBJECT_VALIDATION_BAD_REQUEST);
    }

}
