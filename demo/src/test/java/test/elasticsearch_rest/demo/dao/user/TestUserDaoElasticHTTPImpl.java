package test.elasticsearch_rest.demo.dao.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import test.elasticsearch_rest.demo.exceptions.AppException;
import test.elasticsearch_rest.demo.model.User;

import java.util.Arrays;

public class TestUserDaoElasticHTTPImpl {

    // TODO: Test with DB

    @Test
    public void testCreateWithNewUser() {

        User newUser = new User();
        newUser.setUsername("testCreateWithNewUser");
        newUser.setPassword("testCreateWithNewUser");
        newUser.setEmail("test@test.user");

        try {
            new UserDaoElasticHTTPImpl().create(newUser);
        } finally {
            new UserDaoElasticHTTPImpl().delete(newUser);
        }
    }

    @Test
    public void testCreateWithExistingUser() {

        User newUser = new User();
        newUser.setUsername("testCreateWithExistingUser");
        newUser.setPassword("testCreateWithExistingUser");
        newUser.setEmail("test@test.user");

        User newUser2 = new User();
        newUser2.setUsername("testCreateWithExistingUser");
        newUser2.setPassword("testCreateWithExistingUser");
        newUser2.setEmail("test@test.user");

        try {
            new UserDaoElasticHTTPImpl().create(newUser);
            Assertions.assertThrows(HttpClientErrorException.class, () -> new UserDaoElasticHTTPImpl().create(newUser2));
        } finally {
            new UserDaoElasticHTTPImpl().delete(newUser);
        }
    }

    @Test
    public void testCreateWithNullUser() {
        Assertions.assertThrows(NullPointerException.class, () -> new UserDaoElasticHTTPImpl().create(null));
    }

    @Test
    public void testFindAll() {

        User newUser1 = new User();
        newUser1.setUsername("testFindAll1");
        newUser1.setPassword("testFindAll1");
        newUser1.setEmail("test@test.user");

        User newUser2 = new User();
        newUser2.setUsername("testFindAll2");
        newUser2.setPassword("testFindAll2");
        newUser2.setEmail("test@test.user");

        User newUser3 = new User();
        newUser3.setUsername("testFindAll3");
        newUser3.setPassword("testFindAll3");
        newUser3.setEmail("test@test.user");

        new UserDaoElasticHTTPImpl().create(newUser1);
        new UserDaoElasticHTTPImpl().create(newUser2);
        new UserDaoElasticHTTPImpl().create(newUser3);

        try {
            Assertions.assertEquals(Arrays.asList(newUser1,newUser2,newUser3), new UserDaoElasticHTTPImpl().findAll());
        } finally {
            new UserDaoElasticHTTPImpl().delete(newUser1);
            new UserDaoElasticHTTPImpl().delete(newUser2);
            new UserDaoElasticHTTPImpl().delete(newUser3);
        }
    }

    @Test
    public void testFindByIdWithExistingId() {

        // TODO: Can't create-check-delete ("User not found")

        User newUser = new User();
        newUser.setUsername("testFindByIdWithExistingId");
        newUser.setPassword("testFindByIdWithExistingId");
        newUser.setEmail("test@test.user");
        new UserDaoElasticHTTPImpl().create(newUser);

        try {
            Assertions.assertEquals(new UserDaoElasticHTTPImpl().findById("testFindByIdWithExistingId"), newUser);
        } finally {
            new UserDaoElasticHTTPImpl().delete(newUser);
        }

    }

    @Test
    public void testFindByIdWithNonExistingId() {
        Assertions.assertThrows(AppException.class, () -> new UserDaoElasticHTTPImpl().findById("testFindByIdWithNonExistingId"));
    }

    @Test
    public void testFindByIdWithNullId() {
        // TODO: NullPtnException ?
        Assertions.assertThrows(AppException.class, () -> new UserDaoElasticHTTPImpl().findById(null));
    }

    @Test
    public void testUpdateWithExistingUser() {

        // TODO: Can't create-check-delete ("User not found")

        User newUser = new User();
        newUser.setUsername("testUpdateWithExistingUser");
        newUser.setPassword("testUpdateWithExistingUser");
        newUser.setEmail("test@test.user");

        try {
            new UserDaoElasticHTTPImpl().create(newUser);
            newUser.setPassword("testUpdateWithExistingUser_newInfo");
            new UserDaoElasticHTTPImpl().update(newUser);
            Assertions.assertEquals(new UserDaoElasticHTTPImpl().findById("testUpdateWithExistingUser"), newUser);
        } finally {
            new UserDaoElasticHTTPImpl().delete(newUser);
        }

    }

    @Test
    public void testUpdateWithNonExistingUser() {

        User newUser = new User();
        newUser.setUsername("testUpdateWithNonExistingUser");
        newUser.setPassword("testUpdateWithNonExistingUser");
        newUser.setEmail("test@test.user");

        Assertions.assertThrows(HttpClientErrorException.class, () -> new UserDaoElasticHTTPImpl().update(newUser));

    }

    @Test
    public void testUpdateWithNullUser() {

        Assertions.assertThrows(NullPointerException.class, () -> new UserDaoElasticHTTPImpl().update(null));

    }

    @Test
    public void testDeleteWithExistingUser() {

        User newUser = new User();
        newUser.setUsername("testDeleteWithExistingUser");
        newUser.setPassword("testDeleteWithExistingUser");
        newUser.setEmail("test@test.user");

        try {
            new UserDaoElasticHTTPImpl().create(newUser);
            new UserDaoElasticHTTPImpl().delete(newUser);
            Assertions.assertThrows(AppException.class, () -> new UserDaoElasticHTTPImpl().findById("testDeleteWithExistingUser"));
        } finally {

        }

    }

    @Test
    public void testDeleteWithNonExistingUser() {

        User newUser = new User();
        newUser.setUsername("testDeleteWithNonExistingUser");
        newUser.setPassword("testDeleteWithNonExistingUser");
        newUser.setEmail("test@test.user");

        Assertions.assertThrows(HttpClientErrorException.class, () -> new UserDaoElasticHTTPImpl().delete(newUser));

    }

    @Test
    public void testDeleteWithNullUser() {
        Assertions.assertThrows(NullPointerException.class, () -> new UserDaoElasticHTTPImpl().delete(null));
    }

    @Test
    public void testGetHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Assertions.assertEquals(new UserDaoElasticHTTPImpl().getHeaders(),headers);
    }

    @Test
    public void testSetPayloadWithValidObject() {

        User newUser = new User();
        newUser.setUsername("testSetPayloadWithValidObject");
        newUser.setPassword("testSetPayloadWithValidObject");
        newUser.setEmail("test@test.user");

        Assertions.assertEquals(new UserDaoElasticHTTPImpl().setPayload(newUser)
                , "{\"username\":\"testSetPayloadWithValidObject\",\"password\":\"testSetPayloadWithValidObject\",\"email\":\"test@test.user\"}");

    }

    @Test
    public void testSetPayloadWithEmptyObject() {

        User newUser = new User();

        Assertions.assertEquals(new UserDaoElasticHTTPImpl().setPayload(newUser)
                , "{\"username\":null,\"password\":null,\"email\":null}");

    }

    @Test
    public void testSetPayloadWithNullObject() {

        Assertions.assertEquals(new UserDaoElasticHTTPImpl().setPayload(null)
                , "null");

    }

    @Test
    public void testGetUserWithValidString() {

        User newUser = new User();
        newUser.setUsername("testGetUserWithValidString");
        newUser.setPassword("testGetUserWithValidString");
        newUser.setEmail("test@test.user");

        Assertions.assertEquals(new UserDaoElasticHTTPImpl().getUser("{\"username\":\"testGetUserWithValidString\",\"password\":\"testGetUserWithValidString\",\"email\":\"test@test.user\"}")
                , newUser);
    }

    @Test
    public void testGetUserWithInvalidString() {
        Assertions.assertThrows(AppException.class, () -> new UserDaoElasticHTTPImpl().getUser("{\"incorrect-username\":\"testGetUserWithValidString\",\"incorrect-password\":\"testGetUserWithValidString\",\"incorrect-email\":\"test@test.user\"}"));
    }

    @Test
    public void testGetUserWithNullString() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserDaoElasticHTTPImpl().getUser(null));
    }

}
