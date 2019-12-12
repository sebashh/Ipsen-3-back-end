package udemy.auth;

import udemy.User;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

/**
 * The class that authenticates the credentials for the database using the config.yaml file *
 */

public class PLNTAuthenticator implements Authenticator<BasicCredentials, User> {
    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) {
        if ("plant".equals(basicCredentials.getPassword())){
            return Optional.of(new User(basicCredentials.getUsername()));
        }
        return Optional.empty();
    }
}
