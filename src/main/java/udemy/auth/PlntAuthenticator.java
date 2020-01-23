package udemy.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import udemy.ENUM.Roles;
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


        try {
//            String storedPassword = authController.getPaswordByEmail(basicCredentials.getUsername());
//            System.out.println("result: " + storedPassword);
            authController.verifyPassword(basicCredentials);
                System.out.println(basicCredentials.getPassword()+ "...");
        }
        catch (Exception e) {
            throw new AuthenticationException(e);
        }
        return Optional.empty();
    }
}
