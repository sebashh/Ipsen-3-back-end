package udemy;

import com.udemy.auth.PLNTAuthenticator;
import com.udemy.persistance.ProjectDAO;
import com.udemy.persistance.UploadDAO;
import com.udemy.persistance.UserDAO;
import com.udemy.persistance.UserProjectDAO;
import com.udemy.resources.ProjectResource;
import com.udemy.resources.UploadResource;
import com.udemy.resources.UserProjectResource;
import com.udemy.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jdbi.v3.core.Jdbi;

public class PLNTApplication extends Application<PLNTConfiguration> {

    public static void main(final String[] args) throws Exception {
        new com.udemy.PLNTApplication().run("server", "config.yml");
    }

    @Override
    public String getName() {
        return "DropBookmarks";
    }

    @Override
    public void initialize(final Bootstrap<PLNTConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final PLNTConfiguration configuration,
                    final Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        //nieuw
        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final ProjectDAO projectDAO = jdbi.onDemand(ProjectDAO.class);
        final UploadDAO uploadDAO = jdbi.onDemand(UploadDAO.class);
        final UserProjectDAO userProjectDAO = jdbi.onDemand(UserProjectDAO.class);
        //

        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new PLNTAuthenticator())
                .setRealm("Het ziekenhuis")
                .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

        //nieuw
        environment.jersey().register(new UserResource(userDAO));
        environment.jersey().register(new ProjectResource(projectDAO));
        environment.jersey().register(new UploadResource(uploadDAO));
        environment.jersey().register(new UserProjectResource(userProjectDAO));
        environment.jersey().register(new JsonProcessingExceptionMapper(true));
        //

    }

}
