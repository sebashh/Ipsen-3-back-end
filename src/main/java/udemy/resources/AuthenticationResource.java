package udemy.resources;

import io.dropwizard.auth.basic.BasicCredentials;
import udemy.Controllers.AuthenticationController;
import udemy.Controllers.JWTController;
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

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LoginModel loginModel) {
        try {
            BasicCredentials credentials = new BasicCredentials(loginModel.getEmail(), loginModel.getPassword());
            plntAuthenticator.authenticate(credentials);
            if(authenticationController.verifyPassword(credentials) == true) {
                int userId = authenticationController.getUserIdByEmail(loginModel.getEmail());
                String userRole = authenticationController.getUserRole(userId);
                AuthModel model = JWTController.generateAuthModel(Integer.toString(userId), userRole);

                System.out.println("wachtwoord correct");
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

