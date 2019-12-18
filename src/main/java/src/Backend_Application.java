package src;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import src.auth.PlntAuthenticator;
import src.controllers.AuthenticationController;
import src.core.LoginModel;

public class Backend_Application extends Application<Backend_Configuration> {

    public static void main(final String[] args) throws Exception {
        new Backend_Application().run(args);

        AuthenticationController authenticationController = new AuthenticationController();

        PlntAuthenticator auth = new PlntAuthenticator(authenticationController);
        System.out.println(auth.authenticate(new LoginModel("bla@gmail.com", "hallo")).isPresent());
    }

    @Override
    public String getName() {
        return "Ipsen_3_Back_End";
    }

    @Override
    public void initialize(final Bootstrap<Backend_Configuration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final Backend_Configuration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
//B@41e350f1
//B@41e350f1
