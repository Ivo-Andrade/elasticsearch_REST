package test.elasticsearch_rest.demo.aspects.annotations;

import java.lang.annotation.*;

import org.springframework.stereotype.Component;

@Component
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface IdObjectAdvice {
}
