package udemy.resources;

import io.dropwizard.auth.basic.BasicCredentials;
import org.eclipse.jetty.server.Authentication;
import udemy.Controllers.AuthenticationController;
import udemy.User;
//import udemy.auth.PlntAuthenticator;
import udemy.core.models.LoginModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
    public class AuthenticationResource {
//        private PlntAuthenticator plntAuthenticator;
        private AuthenticationController authenticationController;

//    public AuthenticationResource(AuthenticationController authenticationController, PlntAuthenticator plntAuthenticator) {
//        this.authenticationController = authenticationController;
//        this.plntAuthenticator = plntAuthenticator;
//    }
//
//        @POST
//        @Path("/auth")
//        @Produces(MediaType.APPLICATION_JSON)
//        public Response authenticateUser(LoginModel loginModel) {
//            try {
//                BasicCredentials credentials = new BasicCredentials(loginModel.getEmail(), loginModel.getPassword());
//
//            plntAuthenticator.authenticate(credentials);
//                return Response.ok(200).build();
//            } catch (Exception e) {
//                return Response.status(Response.Status.FORBIDDEN).build();
//            }
//        }

    }

