package test.elasticsearch_rest.demo.exceptions.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import test.elasticsearch_rest.demo.model.APIResponse;

@ControllerAdvice
public class HttpClientErrorExceptionHandler
    extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = { HttpClientErrorException.class })
    protected ResponseEntity<APIResponse> handleHttpClientErrorException(HttpClientErrorException e, WebRequest request ){
        return ResponseEntity.status(e.getStatusCode()).body(new APIResponse("There was an HttpClientErrorException accessing the database: "+ e.getMessage()));
    }
}
