package test.elasticsearch_rest.demo.exceptions.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import test.elasticsearch_rest.demo.exceptions.AppException;
import test.elasticsearch_rest.demo.exceptions.enums.AppExceptions;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException e) {
        return ResponseEntity
            .status(AppExceptions.REQUEST_BODY_VALIDATION_BAD_REQUEST.getStatus())
            .body(new AppException(AppExceptions.REQUEST_BODY_VALIDATION_BAD_REQUEST));
    }
}
