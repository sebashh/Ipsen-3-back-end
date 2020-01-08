package src.resources;

import src.api.User;
import src.auth.PlntAuthenticator;
import src.controllers.AuthenticationController;
import src.core.LoginModel;
import src.core.UserModel;
import src.db.UserDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/authentication")
    public class AuthenticationResource {
        private PlntAuthenticator plntAuthenticator;

        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response authenticateUser(LoginModel loginModel) {
            try {
            plntAuthenticator.authenticate(loginModel);


                return Response.ok(/**token*/).build();
            } catch (Exception e) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }

    }

