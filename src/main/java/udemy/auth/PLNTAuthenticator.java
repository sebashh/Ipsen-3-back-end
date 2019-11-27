package udemy.auth;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import udemy.core.models.User;

import java.util.Optional;

public class PLNTAuthenticator implements Authenticator<BasicCredentials, User> {
    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) {
        if ("plant".equals(basicCredentials.getPassword())){
            return Optional.of(new User(basicCredentials.getUsername()));
        }
        return Optional.empty();
    }
}
