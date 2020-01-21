package udemy.resources;

import io.dropwizard.auth.basic.BasicCredentials;
import org.eclipse.jetty.server.Authentication;
import udemy.Controllers.AuthenticationController;
import udemy.Controllers.JWTController;
import udemy.User;
import udemy.auth.AuthModel;
import udemy.auth.PlntAuthenticator;
import udemy.core.models.LoginModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
    public class AuthenticationResource {
        private PlntAuthenticator plntAuthenticator;
        private AuthenticationController authenticationController;

    public AuthenticationResource(AuthenticationController authenticationController, PlntAuthenticator plntAuthenticator) {
        this.authenticationController = authenticationController;
        this.plntAuthenticator = plntAuthenticator;
    }

        @POST
        @Path("/login")
        @Produces(MediaType.APPLICATION_JSON)
        public Response authenticateUser(LoginModel loginModel) {
            try {
                BasicCredentials credentials = new BasicCredentials(loginModel.getEmail(), loginModel.getPassword());
//                authenticationController.verifyPassword(new BasicCredentials(loginModel.getEmail(), loginModel.getPassword()))
                if(true) {
                    int userId = authenticationController.getUserIdByEmail(loginModel.getEmail());
                    String userRole = authenticationController.getUserRole(userId);
                    AuthModel model = JWTController.generateAuthModel(Integer.toString(userId), userRole);

                    return Response.ok(200)
                            .entity(model)
                            .build();
                }
                else{
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
            } catch (Exception e) {
                System.out.println(e);
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }

    }

