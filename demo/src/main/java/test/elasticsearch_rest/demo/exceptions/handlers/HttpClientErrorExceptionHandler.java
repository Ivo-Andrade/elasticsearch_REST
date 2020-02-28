package test.elasticsearch_rest.demo.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import test.elasticsearch_rest.demo.exceptions.AppException;
import test.elasticsearch_rest.demo.exceptions.enums.AppExceptions;

@ControllerAdvice
public class HttpClientErrorExceptionHandler {

    @ExceptionHandler(value = { HttpClientErrorException.class })
    protected ResponseEntity handleHttpClientErrorException(HttpClientErrorException e){
        if(e.getStatusCode().equals(HttpStatus.CONFLICT))
            return ResponseEntity.status(AppExceptions.USER_DAO_CONFLICT.getStatus()).body(new AppException(AppExceptions.USER_DAO_CONFLICT));
        else if(e.getStatusCode().equals(HttpStatus.NOT_FOUND))
            return ResponseEntity.status(AppExceptions.USER_NOT_FOUND.getStatus()).body(new AppException(AppExceptions.USER_NOT_FOUND));
        else{
            e.printStackTrace();
            return ResponseEntity.status(AppExceptions.USER_DAO_INTERNAL.getStatus()).body(new AppException(AppExceptions.USER_DAO_INTERNAL));
        }
    }
}
