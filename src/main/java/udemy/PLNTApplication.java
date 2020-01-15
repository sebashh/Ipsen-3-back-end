package udemy;

import udemy.Controllers.PaperController;
import udemy.Controllers.ProjectController;
import udemy.Controllers.StatisticsController;
import udemy.auth.PLNTAuthenticator;
import udemy.persistance.*;
import udemy.resources.*;
import udemy.services.BackupService;
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
import udemy.services.CorsFilter;

/**
 * The main class used to start the application
 * Every neccessary class will be created here including the DAO, Controllers
 * The resource classes are also registered to the jersey environment
 * which allow the paths to be exposed by jersey for HTTP requests
 */
public class PLNTApplication extends Application<PLNTConfiguration> {

    public static void main(final String[] args) throws Exception {
        new PLNTApplication().run("server", "config.yml");
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


        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(new PLNTAuthenticator())
                .setRealm("Het ziekenhuis")
                .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new JsonProcessingExceptionMapper(true));
        environment.jersey().register(CorsFilter.class);


        final ProjectDAO projectDAO = jdbi.onDemand(ProjectDAO.class);
        final PaperDAO paperDAO = jdbi.onDemand(PaperDAO.class);
        final StatisticsDAO statisticsDAO = jdbi.onDemand(StatisticsDAO.class);
        final StatisticsController statisticsController = new StatisticsController(statisticsDAO);
        final PaperController paperController = new PaperController(paperDAO);
        final ProjectController projectController = new ProjectController(projectDAO);
        environment.jersey().register(new StatisticsResource(statisticsController));
        environment.jersey().register(new PaperResource(paperController));
        environment.jersey().register(new ProjectResource(projectController));
        BackupService backupService = new BackupService();

    }

}
