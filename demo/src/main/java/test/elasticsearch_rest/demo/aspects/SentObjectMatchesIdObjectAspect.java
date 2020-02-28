package test.elasticsearch_rest.demo.aspects;

import br.com.looplex.ElasticsearchSecurityDAOService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.elasticsearch_rest.demo.exceptions.AppException;
import test.elasticsearch_rest.demo.exceptions.enums.AppExceptions;
import test.elasticsearch_rest.demo.model.User;

@Component
public class SentObjectMatchesIdObjectAspect {

    @Autowired
    ElasticsearchSecurityDAOService userDaoService;

    @Before("@annotation(test.elasticsearch_rest.demo.aspects.annotations.SentObjectMatchesIdObjectAdvice) && args(@PathVariable(value=\"id\") id, @RequestBody persistentObject)")
    public void idDeleteObjectValidation(JoinPoint joinPoint, String id, User persistentObject) {
        if( !userDaoService.findById(id).equals(persistentObject) )
            throw new AppException(AppExceptions.ID_OBJECT_VALIDATION_BAD_REQUEST);
    }

}
