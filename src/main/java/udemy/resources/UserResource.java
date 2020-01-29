package udemy.resources;

import io.dropwizard.auth.Auth;
import udemy.Controllers.UserController;
import udemy.auth.AuthUser;
import udemy.core.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/ipsen3users")
public class UserResource {
    private UserController userController;

    public UserResource(UserController userController) {
        this.userController = userController;
    }

    @POST
    @Path("/client")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postClient(User user){
        if(userController.uploadClient(user))
        return Response.status(200)
                .entity(true)
                .build();
        else return Response.status(Response.Status.NOT_ACCEPTABLE)
                .entity(false)
                .build();
    }

    @POST
    @Path("/student")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postStudent(User user){
        if(userController.uploadStudent(user)){
            return Response.status(200)
                    .entity(true)
                    .build();
        }
        else{
            return Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity(false)
                    .build();
        }

    }

    @POST
    @Path("/teacher")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postTeacher(User user){
        if(userController.uploadTeacher(user)){
            return Response.status(200)
                    .entity(true)
                    .build();
        }
        else return Response.status(Response.Status.NOT_ACCEPTABLE)
                .entity(false)
                .build();
    }

    @GET
    @Path("/email")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserEmailById(@Auth Optional<AuthUser> user){
        int userId = Integer.parseInt(user.get().getName());
        String userEmail = userController.getUserEmail(userId);
        return Response.status(200).entity(userEmail).build();
    }

}
