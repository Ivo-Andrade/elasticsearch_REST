package test.elasticsearch_rest.demo.aspects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.elasticsearch_rest.demo.exceptions.AppException;
import test.elasticsearch_rest.demo.model.User;

public class TestSentObjectMatchesIdObjectAspect {

    @Test
    public void testIdDeleteObjectValidationWithValidInput() {
        User newUser = new User();
        newUser.setUsername("testId");
        new IdEqualsSentObjectIdAspect().idUpdateObjectValidation(null, "testId", newUser);
    }

    @Test
    public void testIdDeleteObjectValidationWithInvalidInput() {
        User newUser = new User();
        newUser.setUsername("testId");
        Assertions.assertThrows(AppException.class, () -> new IdEqualsSentObjectIdAspect().idUpdateObjectValidation(null, "testDifferentId", newUser));
    }

    @Test
    public void testIdDeleteObjectValidationWithNullId() {
        User newUser = new User();
        newUser.setUsername("testId");
        Assertions.assertThrows(NullPointerException.class, () -> new IdEqualsSentObjectIdAspect().idUpdateObjectValidation(null, null, newUser));
    }

    @Test
    public void testIdDeleteObjectValidationWithNullUser() {
        Assertions.assertThrows(NullPointerException.class, () -> new IdEqualsSentObjectIdAspect().idUpdateObjectValidation(null, "testId", null));
    }

    @Test
    public void testIdDeleteObjectValidationWithNullUsername() {
        User newUser = new User();
        Assertions.assertThrows(AppException.class, () -> new IdEqualsSentObjectIdAspect().idUpdateObjectValidation(null, "testId", newUser));
    }

}
