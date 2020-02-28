package test.elasticsearch_rest.demo.aspects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.elasticsearch_rest.demo.exceptions.AppException;
import test.elasticsearch_rest.demo.model.User;

public class TestIdEqualsSentObjectIdAspect {

    @Test
    public void testIdUpdateObjectValidationWithValidInput() {
        User newUser = new User();
        newUser.setUsername("testId");
        new SentObjectMatchesIdObjectAspect().idDeleteObjectValidation(null, "testId", newUser);
    }

    @Test
    public void testIdUpdateObjectValidationWithInvalidInput() {
        User newUser = new User();
        newUser.setUsername("testId");
        Assertions.assertThrows(AppException.class, () -> new SentObjectMatchesIdObjectAspect().idDeleteObjectValidation(null, "testDifferentId", newUser));
    }

    @Test
    public void testIdUpdateObjectValidationWithNullId() {
        User newUser = new User();
        newUser.setUsername("testId");
        Assertions.assertThrows(NullPointerException.class, () -> new SentObjectMatchesIdObjectAspect().idDeleteObjectValidation(null, null, newUser));
    }

    @Test
    public void testIdUpdateObjectValidationWithNullUser() {
        User newUser = new User();
        newUser.setUsername("testId");
        Assertions.assertThrows(NullPointerException.class, () -> new SentObjectMatchesIdObjectAspect().idDeleteObjectValidation(null, "testId", null));
    }

    @Test
    public void testIdUpdateObjectValidationWithNullUsername() {
        User newUser = new User();
        Assertions.assertThrows(AppException.class, () -> new SentObjectMatchesIdObjectAspect().idDeleteObjectValidation(null, "testId", newUser));
    }

}
