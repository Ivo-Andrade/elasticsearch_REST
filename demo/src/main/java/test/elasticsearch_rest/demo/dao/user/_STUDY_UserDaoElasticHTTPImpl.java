//package test.elasticsearch_rest.demo.dao.user;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import test.elasticsearch_rest.demo.exceptions.AppException;
//import test.elasticsearch_rest.demo.exceptions.enums.AppExceptions;
//import test.elasticsearch_rest.demo.model.User;
//
//@Component
//@Qualifier("http-study")
//public class _STUDY_UserDaoElasticHTTPImpl extends UserDao {
//
//    RestTemplate restTemplate = new RestTemplate();
//
//    public void create(User newInstance) {
//
//        String postRequestURL = "http://localhost:9200/looplex-users/_create/" + newInstance.getUsername();
//
//        String objectJson;
//
//        try {
//            objectJson = new ObjectMapper().writeValueAsString(newInstance);
//        } catch (JsonProcessingException e) {
//            throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> request = new HttpEntity<>(objectJson, headers);
//
//        String result;
//
//        try {
//            result = restTemplate.exchange(postRequestURL, HttpMethod.POST, request, String.class).getBody();
//        } catch (HttpClientErrorException e) {
//            if( e.getStatusCode().equals(HttpStatus.CONFLICT))
//                throw new AppException(AppExceptions.USER_DAO_CONFLICT);
//            else throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//        }
//
//        JsonNode resultJson;
//
//        try {
//            resultJson = new ObjectMapper().readTree(result);
//        } catch (JsonProcessingException e) {
//            throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//        }
//
//        if( resultJson.has("error") ) {
//            throw new AppException(AppExceptions.USER_DAO_INTERNAL, resultJson.get("error").asText());
//        }
//
//    }
//
//    @Override
//    public List<User> findAll() {
//
//        String getRequestURL = "http://localhost:9200/looplex-users/_search";
//
//        String result = restTemplate.getForObject(getRequestURL, String.class);
//
//        JsonNode resultJson;
//
//        try {
//            resultJson = new ObjectMapper().readTree(result);
//        } catch (JsonProcessingException e) {
//            throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//        }
//
//        if( resultJson.has("error") ) {
//            throw new AppException(AppExceptions.USER_DAO_INTERNAL, resultJson.get("error").asText());
//        }
//
//        else if( resultJson.has("hits") ) {
//            try {
//                List<User> userList = new ArrayList<User>();
//                for(JsonNode hit : resultJson.get("hits").get("hits")){
//                    userList.add(new ObjectMapper().readValue(hit.get("_source").toString(), User.class));
//                }
//                return userList;
//            } catch (JsonProcessingException e) {
//                throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//            }
//        }
//
//        else throw new AppException(AppExceptions.USER_DAO_INTERNAL, resultJson.toString());
//
//    }
//
//    @Override
//    public User findById(String id) {
//
//        String getRequestURL = "http://localhost:9200/looplex-users/_search?q=username:" + id;
//
//        String result = restTemplate.getForObject(getRequestURL, String.class);
//
//        JsonNode resultJson;
//
//        try {
//            resultJson = new ObjectMapper().readTree(result);
//        } catch (JsonProcessingException e) {
//            throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//        }
//
//        if( resultJson.has("error") ) {
//            throw new AppException(AppExceptions.USER_DAO_INTERNAL, resultJson.get("error").asText());
//        }
//
//        else if( resultJson.has("hits") ) {
//            try {
//                return new ObjectMapper().readValue(resultJson.get("hits").get("hits").get(0).get("_source").toString(), User.class);
//            } catch (JsonProcessingException e) {
//                throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//            }
//        }
//
//        else throw new AppException(AppExceptions.USER_DAO_INTERNAL, resultJson.toString());
//
//    }
//
//    public User update(User transientObject) {
//
//        String putRequestURL = "http://localhost:9200/looplex-users/_update/" + transientObject.getUsername() + "?_source";
//
//        String objectJson;
//
//        try {
//            objectJson = "{\"doc\":" + new ObjectMapper().writeValueAsString(transientObject) + "}";
//        } catch (JsonProcessingException e) {
//            throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> request = new HttpEntity<>(objectJson, headers);
//
//        String result;
//
//        try {
//            result = restTemplate.exchange(putRequestURL, HttpMethod.POST, request, String.class).getBody();
//        } catch (HttpClientErrorException e) {
//            throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//        }
//
//        JsonNode resultJson;
//
//        try {
//            resultJson = new ObjectMapper().readTree(result);
//        } catch (JsonProcessingException e) {
//            throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//        }
//
//        if( resultJson.has("error") ) {
//            throw new AppException(AppExceptions.USER_DAO_INTERNAL, resultJson.get("error").asText());
//        }
//
//        else if( resultJson.has("get") ) {
//            try {
//                return new ObjectMapper().readValue(resultJson.get("get").get("_source").toString(), User.class);
//            } catch (JsonProcessingException e) {
//                throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//            }
//        }
//
//        else throw new AppException(AppExceptions.USER_DAO_INTERNAL, resultJson.toString());
//
//    }
//
//    @Override
//    public void delete(User persistentObject) {
//        String deleteRequestURL = "http://localhost:9200/looplex-users/_doc/" + persistentObject.getUsername();
//
//        String result;
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> request = new HttpEntity<>("", headers);
//
//        if( findById(persistentObject.getUsername()) != null) {
//
//            if(persistentObject.equals(findById(persistentObject.getUsername()))) {
//
//                try {
//                    result = restTemplate.exchange(deleteRequestURL, HttpMethod.DELETE, request, String.class).getBody();
//                } catch (HttpClientErrorException e) {
//                    throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//                }
//
//            }  else throw new AppException(AppExceptions.USER_DAO_INTERNAL);
//
//        } else throw new AppException(AppExceptions.USER_DAO_INTERNAL);
//
//        JsonNode resultJson;
//
//        try {
//            resultJson = new ObjectMapper().readTree(result);
//        } catch (JsonProcessingException e) {
//            throw new AppException(AppExceptions.USER_DAO_INTERNAL, e.getMessage());
//        }
//
//        if( resultJson.has("result") ) {
//            if( resultJson.get("result").asText().equals("deleted") ) return;
//            else throw new AppException(AppExceptions.USER_DAO_INTERNAL, resultJson.get("error").asText());
//        }
//
//        else throw new AppException(AppExceptions.USER_DAO_INTERNAL, resultJson.toString());
//
//    }
//
//}