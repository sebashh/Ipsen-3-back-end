package src.resources;

import src.api.User;
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
        private AuthenticationController authController;

        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response authenticateUser(LoginModel loginModel) {
            try {
            authController.loginUser(loginModel.getEmail(), loginModel.getPassword());

//                String token = issueToken(email);

                return Response.ok(/**token*/).build();
            } catch (Exception e) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }


        private void authenticate(String email, String password) {
            // Authenticate against a database, LDAP, file or whatever
            // Throw an Exception if the credentials are invalid
        }

//        private String issueToken(String email) {
            // Issue a token (can be a random String persisted to a database or a JWT token)
            // The issued token must be associated to a user
            // Return the issued token
//        }
    }

