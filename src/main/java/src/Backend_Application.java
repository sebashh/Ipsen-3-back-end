package src;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import src.controllers.AuthenticationController;

public class Backend_Application extends Application<Backend_Configuration> {

    public static void main(final String[] args) throws Exception {
        new Backend_Application().run(args);
        AuthenticationController authenticationController = new AuthenticationController();
        authenticationController.loginUser("bla@gmail.com", "wachtwoord");
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
