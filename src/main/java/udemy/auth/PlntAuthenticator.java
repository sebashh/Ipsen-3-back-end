package udemy.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import udemy.User;
import udemy.Controllers.AuthenticationController;
import java.util.Optional;

public class PlntAuthenticator implements Authenticator<BasicCredentials, User> {

    private final AuthenticationController authController;

    public PlntAuthenticator(AuthenticationController authController) {
        this.authController = authController;
    }

    @Override
    public final Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {

        Optional<User> result;

        try {
            result = Optional.of(authController.getUserByEmail(basicCredentials.getUsername()));
            if (!result.isPresent()) {
                return result;
            } else if (authController.passwordValidator(basicCredentials.getPassword(), result.get().getPassword()) == true) {
                return result;
            } else {
                return Optional.empty();
            }
        }
        catch (Exception e) {
            throw new AuthenticationException(e);
        }
    }
}
