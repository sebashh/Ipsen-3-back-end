//package Ipsen_3_Back_End.controller;
//
//import io.dropwizard.auth.AuthenticationException;
//import io.dropwizard.auth.basic.BasicCredentials;
//import org.junit.Assert;
//import org.junit.ClassRule;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import udemy.Controllers.AuthenticationController;
//import udemy.persistance.UserDAO;
//
//public class loginTest {
//
//    private static final UserDAO = mock(UserDAO.class);
//
//    @ClassRule
//    public static
//
//    @BeforeEach
//    public void setUp() {
//        authenticationController = new AuthenticationController(userDAO);
//    }
//
//    @Test
//    public void testVerification() throws AuthenticationException {
//        BasicCredentials basicCredentials = new BasicCredentials("Test@Teacher.com", "test");
//        Assert.assertEquals(true, authenticationController.verifyPassword(basicCredentials));
//    }
//}
