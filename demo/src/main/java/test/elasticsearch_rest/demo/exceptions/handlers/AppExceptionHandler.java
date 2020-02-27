package test.elasticsearch_rest.demo.exceptions.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import test.elasticsearch_rest.demo.exceptions.AppException;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = { AppException.class })
    protected ResponseEntity handleAppException(AppException e){
        return ResponseEntity.status(e.getStatus()).body(e);
    }
}
