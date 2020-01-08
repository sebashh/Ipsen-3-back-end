package src.auth;

import io.dropwizard.auth.Authorizer;
import src.api.User;

public class PlntAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return user.getUserRole() != null && user.getUserRole().equals(role);
    }
}
