package udemy.auth;

import io.dropwizard.auth.Authorizer;
import udemy.User;

public class PlntAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return user.getUserrole() != null && user.getUserrole().equals(role);
    }
}
