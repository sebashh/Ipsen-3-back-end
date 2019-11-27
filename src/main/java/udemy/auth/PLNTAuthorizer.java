package udemy.auth;

import com.udemy.User;
import io.dropwizard.auth.Authorizer;


public class PLNTAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String s) {
        return user.getName().equals("PLNT");
    }
}
