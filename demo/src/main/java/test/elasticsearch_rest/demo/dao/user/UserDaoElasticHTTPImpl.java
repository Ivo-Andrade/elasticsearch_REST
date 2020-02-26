package test.elasticsearch_rest.demo.dao.user;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.elasticsearch.action.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import test.elasticsearch_rest.demo.exceptions.*;
import test.elasticsearch_rest.demo.model.ElasticsearchUpdatePayload;
import test.elasticsearch_rest.demo.model.User;

import javax.validation.constraints.NotBlank;

@Component
@Qualifier("http-final")
public class UserDaoElasticHTTPImpl extends UserDao {

    RestTemplate restTemplate = new RestTemplate();

    public void create(User newInstance) {

        restTemplate.exchange(
            "http://localhost:9200/looplex-users/_create/" + newInstance.getUsername()
            , HttpMethod.POST
            , new HttpEntity<String>(setPayload(newInstance), getHeaders())
            , JsonNode.class
        );

    }

    @Override
    public List<User> findAll() {

        ResponseEntity<JsonNode> requestResponse;

        requestResponse = restTemplate.exchange(
            "http://localhost:9200/looplex-users/_search"
            , HttpMethod.GET
            , new HttpEntity<String>(null, getHeaders())
            , JsonNode.class
        );

        List<User> userList = new ArrayList<User>();
        for (JsonNode hit : requestResponse.getBody().get("hits").get("hits")) {
            userList.add(
                getUser(hit.get("_source").toString())
            );
        }
        return userList;

    }

    @Override
    public User findById(String id) {

        ResponseEntity<JsonNode> requestResponse;

        requestResponse = restTemplate.exchange(
            "http://localhost:9200/looplex-users/_search?q=username:" + id
            , HttpMethod.GET
            , new HttpEntity<String>(null, getHeaders())
            , JsonNode.class
        );

        if(requestResponse.getBody().get("hits").get("hits").size() > 0)
            return getUser(requestResponse.getBody().get("hits").get("hits").get(0).get("_source").toString());
        else throw new UserNotFoundException("User not found!");

    }

    public User update(User transientObject) {

        ResponseEntity<JsonNode> requestResponse;

        requestResponse = restTemplate.exchange(
                "http://localhost:9200/looplex-users/_update/" + transientObject.getUsername() + "?_source"
                , HttpMethod.POST
                , new HttpEntity<String>(setPayload(new ElasticsearchUpdatePayload(transientObject)), getHeaders())
                , JsonNode.class
        );

        return getUser(requestResponse.getBody().get("get").get("_source").toString());
    }

    @Override
    public void delete(User persistentObject) {

        restTemplate.exchange(
            "http://localhost:9200/looplex-users/_doc/" + persistentObject.getUsername()
            , HttpMethod.DELETE
            , new HttpEntity<String>(null, getHeaders())
            , JsonNode.class
        );

    }

    // TODO: Auxiliary Functions Addressing (New class, stay here?)

    public HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public String setPayload(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new UserDaoInternalException("JsonProcessingException while converting User object to Json string: " + e.getMessage());
        }
    }

    public User getUser(String str) {
        try {
            return new ObjectMapper().readValue(str, User.class);
        } catch (JsonProcessingException e) {
            throw new UserDaoInternalException("JsonProcessingException while converting string to User object: " + e.getMessage());
        }
    }

}