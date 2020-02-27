package test.elasticsearch_rest.demo.exceptions.enums;

import org.springframework.http.HttpStatus;

public enum AppExceptions {

    ID_OBJECT_VALIDATION_BAD_REQUEST("Id doesn't match payload!", HttpStatus.BAD_REQUEST)
    , USER_DAO_CONFLICT("User already exists in database!", HttpStatus.CONFLICT)
    , USER_NOT_FOUND("User not found!", HttpStatus.NOT_FOUND)
    , USER_DAO_INTERNAL("An unexpected error occured!", HttpStatus.INTERNAL_SERVER_ERROR)
    , REQUEST_BODY_VALIDATION_BAD_REQUEST("Input sent in payload has invalid fields!", HttpStatus.BAD_REQUEST);

    private String message;
    private HttpStatus status;

    AppExceptions(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

}
