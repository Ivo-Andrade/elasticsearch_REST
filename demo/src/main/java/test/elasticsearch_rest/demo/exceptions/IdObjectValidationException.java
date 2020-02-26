package test.elasticsearch_rest.demo.exceptions;

        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdObjectValidationException
        extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    public IdObjectValidationException() {
        super();
    }

    public IdObjectValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdObjectValidationException(String message) {
        super(message);
    }

    public IdObjectValidationException(Throwable cause) {
        super(cause);
    }

}