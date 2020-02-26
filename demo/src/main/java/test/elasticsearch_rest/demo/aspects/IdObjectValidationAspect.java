package test.elasticsearch_rest.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.*;
import test.elasticsearch_rest.demo.exceptions.IdObjectValidationException;
import test.elasticsearch_rest.demo.model.User;

@Aspect
@Component
public class IdObjectValidationAspect {

    @Before("@annotation(test.elasticsearch_rest.demo.aspects.annotations.IdObjectAdvice) && args(@PathVariable(value=\"id\") id, @RequestBody transientObject)")
    public void idUpdateObjectValidation(JoinPoint joinPoint, String id, User transientObject) {
        if( !id.equals(transientObject.getUsername()) )
            throw new IdObjectValidationException("Id doesn't match payload!");
    }

    @Before("@annotation(test.elasticsearch_rest.demo.aspects.annotations.IdObjectAdvice) && args(@PathVariable(value=\"id\") id, @RequestBody persistentObject)")
    public void idDeleteObjectValidation(JoinPoint joinPoint, String id, User persistentObject) {
        if( !id.equals(persistentObject.getUsername()) )
            throw new IdObjectValidationException("Id doesn't match payload!");
    }

}
