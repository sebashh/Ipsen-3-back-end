//package udemy.auth;
//
//import io.dropwizard.auth.AuthFilter;
//import io.dropwizard.auth.AuthenticationException;
//import udemy.User;
//import udemy.core.models.LoginModel;
//
//import javax.annotation.Priority;
//import javax.ws.rs.Priorities;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.container.PreMatching;
//import javax.ws.rs.core.HttpHeaders;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.Provider;
//import java.io.IOException;
//import java.util.Date;
//import java.util.Optional;
//
//@PreMatching
//@Priority(Priorities.AUTHENTICATION)
//public class AuthenticationFilter extends AuthFilter<LoginModel, User> {
//    private PlntAuthenticator authenticator;
//
//    public AuthenticationFilter(PlntAuthenticator authenticator) {
//        this.authenticator = authenticator;
//    }
//
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//        Optional<User> authenticatedUser;
//
//        try {
//            LoginModel credentials = getCredentials(requestContext);
//            authenticatedUser = authenticator.authenticate(credentials);
//        } catch (AuthenticationException e) {
//            throw new WebApplicationException("Unable to validate credentials", Response.Status.UNAUTHORIZED);
//        }
//    }
//
//    private LoginModel getCredentials(ContainerRequestContext requestContext) {
//        LoginModel credentials = new LoginModel();
//
//        try {
//            String rawEmail = requestContext
//                    .getCookies()
//                    .get("auth_email")
//                    .getValue();
//
//            String rawPassword = requestContext
//                    .getCookies()
//                    .get("auth_password")
//                    .getValue();
//
//            credentials.setEmail(String.valueOf(rawEmail));
//            credentials.setPassword(String.valueOf(rawPassword));
//        } catch (Exception e) {
//            throw new WebApplicationException("Unable to parse credentials", Response.Status.UNAUTHORIZED);
//        }
//        return credentials;
//    }
//}
