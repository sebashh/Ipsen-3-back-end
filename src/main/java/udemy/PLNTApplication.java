package udemy;

import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import udemy.Controllers.*;
import udemy.auth.AccountAuthorizationFilter;
import udemy.auth.AuthUser;
import udemy.auth.PlntAuthenticator;
import udemy.core.models.LoginModel;
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
        environment.jersey().register(new JsonProcessingExceptionMapper(true));
        environment.jersey().register(CorsFilter.class);


        //generating DOA
        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final ProjectDAO projectDAO = jdbi.onDemand(ProjectDAO.class);
        final PaperDAO paperDAO = jdbi.onDemand(PaperDAO.class);
        final StatisticsDAO statisticsDAO = jdbi.onDemand(StatisticsDAO.class);

        //generating Controller
        final JWTController jwtController = new JWTController();
        final ProjectController projectController = new ProjectController(projectDAO);
        final AuthenticationController authenticationController = new AuthenticationController(userDAO);
        final UserController userController = new UserController(userDAO);
        final StudentDAO studentDAO = jdbi.onDemand(StudentDAO.class);
        final StudyDAO studyDAO = jdbi.onDemand(StudyDAO.class);
        final StudyController studyController = new StudyController(studyDAO);
        final StudentController studentController = new StudentController(userDAO,studentDAO,studyDAO);
        environment.jersey().register(new StudentResource(studentController));
        final TeacherDAO teacherDAO = jdbi.onDemand(TeacherDAO.class);
        final TeacherController teacherController = new TeacherController(userDAO, teacherDAO, studyDAO);
        environment.jersey().register(new TeacherResource(teacherController));
        final ClientDAO clientDAO = jdbi.onDemand(ClientDAO.class);
        final ClientController clientController = new ClientController(userDAO, clientDAO);
        environment.jersey().register(new ClientResource(clientController));
        final StatisticsController statisticsController = new StatisticsController(statisticsDAO);
        final PaperController paperController = new PaperController(paperDAO);
        final CategoryDAO categoryDAO = jdbi.onDemand(CategoryDAO.class);
        final CategoryController categoryController = new CategoryController(categoryDAO);

        environment.jersey().register(new StatisticsResource(statisticsController));
        environment.jersey().register(new ProjectResource(projectController));
        environment.jersey().register(new PaperResource(paperController));
        PlntAuthenticator plntAuthenticator = new PlntAuthenticator(authenticationController);
        environment.jersey().register(new AuthenticationResource(authenticationController, plntAuthenticator));
//        BackupService backupService = new BackupService();

        //authentication
        environment.jersey().register(new AuthDynamicFeature(AccountAuthorizationFilter.class));
        environment.jersey().register(AccountAuthorizationFilter.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(AuthUser.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);


        environment.jersey().register(new ProjectResource(projectController));
        environment.jersey().register(new UserResource(userController));
        environment.jersey().register(new StudyResource(studyController));
        environment.jersey().register(new CategoryResource(categoryController));


    }

}
