package src.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import src.api.User;
import src.controllers.AuthenticationController;
import src.core.LoginModel;
import java.util.Optional;

public class PlntAuthenticator implements Authenticator<LoginModel, User> {

    private final AuthenticationController authController;

    public PlntAuthenticator(final AuthenticationController authController) {
        this.authController = authController;
    }

    @Override
    public final Optional<User> authenticate(LoginModel loginModel) throws AuthenticationException {
        Optional<User> result;
        try {
            result = Optional.of(authController.getUserByEmail(loginModel.getEmail()));

            if (!result.isPresent()) {
                return result;
            } else if (authController.passwordValidator(loginModel.getPassword(), result.get().getPassword()) == true) {
                System.out.println(result.get().getEmail());
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
