package test.elasticsearch_rest.demo.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import test.elasticsearch_rest.demo.exceptions.enums.AppExceptions;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AppException
    extends RuntimeException
{

    private HttpStatus status;

    public AppException(AppExceptions exception) {
        super(exception.getMessage());
        this.status = exception.getStatus();
    }

    public AppException(AppExceptions exception, Throwable cause) {
        super(exception.getMessage(), cause);
        this.status = exception.getStatus();
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public StackTraceElement[] getStackTrace() { return null; }

}