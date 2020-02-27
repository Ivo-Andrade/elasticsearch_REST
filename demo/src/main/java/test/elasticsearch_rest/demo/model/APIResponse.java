package test.elasticsearch_rest.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class APIResponse<T> {

    private String message;
    private T payload;

    public APIResponse(String message) {
        this.message = message;
    }

    public APIResponse(String message, T payload) {
        this.message = message;
        this.payload = payload;
//        try {
//            this.payload = new ObjectMapper().readTree(payload);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }

}