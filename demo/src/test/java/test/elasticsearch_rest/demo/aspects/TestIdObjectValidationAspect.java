//package test.elasticsearch_rest.demo.aspects;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import test.elasticsearch_rest.demo.exceptions.AppException;
//import test.elasticsearch_rest.demo.model.User;
//
//public class TestIdObjectValidationAspect {
//
//    @Test
//    public void testIdUpdateObjectValidationWithValidInput() {
//        User newUser = new User();
//        newUser.setUsername("testId");
//        new IdObjectValidationAspect().idDeleteObjectValidation(null, "testId", newUser);
//    }
//
//    @Test
//    public void testIdUpdateObjectValidationWithInvalidInput() {
//        User newUser = new User();
//        newUser.setUsername("testId");
//        Assertions.assertThrows(AppException.class, () -> new IdObjectValidationAspect().idDeleteObjectValidation(null, "testDifferentId", newUser));
//    }
//
//    @Test
//    public void testIdUpdateObjectValidationWithNullId() {
//        User newUser = new User();
//        newUser.setUsername("testId");
//        Assertions.assertThrows(NullPointerException.class, () -> new IdObjectValidationAspect().idDeleteObjectValidation(null, null, newUser));
//    }
//
//    @Test
//    public void testIdUpdateObjectValidationWithNullUser() {
//        User newUser = new User();
//        newUser.setUsername("testId");
//        Assertions.assertThrows(NullPointerException.class, () -> new IdObjectValidationAspect().idDeleteObjectValidation(null, "testId", null));
//    }
//
//    @Test
//    public void testIdUpdateObjectValidationWithNullUsername() {
//        User newUser = new User();
//        Assertions.assertThrows(AppException.class, () -> new IdObjectValidationAspect().idDeleteObjectValidation(null, "testId", newUser));
//    }
//
//    @Test
//    public void testIdDeleteObjectValidationWithValidInput() {
//        User newUser = new User();
//        newUser.setUsername("testId");
//        new IdObjectValidationAspect().idUpdateObjectValidation(null, "testId", newUser);
//    }
//
//    @Test
//    public void testIdDeleteObjectValidationWithInvalidInput() {
//        User newUser = new User();
//        newUser.setUsername("testId");
//        Assertions.assertThrows(AppException.class, () -> new IdObjectValidationAspect().idUpdateObjectValidation(null, "testDifferentId", newUser));
//    }
//
//    @Test
//    public void testIdDeleteObjectValidationWithNullId() {
//        User newUser = new User();
//        newUser.setUsername("testId");
//        Assertions.assertThrows(NullPointerException.class, () -> new IdObjectValidationAspect().idUpdateObjectValidation(null, null, newUser));
//    }
//
//    @Test
//    public void testIdDeleteObjectValidationWithNullUser() {
//        Assertions.assertThrows(NullPointerException.class, () -> new IdObjectValidationAspect().idUpdateObjectValidation(null, "testId", null));
//    }
//
//    @Test
//    public void testIdDeleteObjectValidationWithNullUsername() {
//        User newUser = new User();
//        Assertions.assertThrows(AppException.class, () -> new IdObjectValidationAspect().idUpdateObjectValidation(null, "testId", newUser));
//    }
//
//}
