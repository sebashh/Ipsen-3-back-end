package udemy.resources;

import udemy.Controllers.UserController;
import udemy.core.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Resource class for users
 * General path is defined as /user
 * and subpaths are related to the methods
 * With pathparams certain simple values can be passed
 * and the @consume can be used to receive objects in json format
 * @produces is used to create the aforementioned json
 */

@Path("/user")
public class UserResource {

    private UserController userController;

    public UserResource(UserController userController){
        this.userController= userController;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public List<User> getAllUsers(){
        return userController.getAllUsers();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public User getUserById(@PathParam("id") int id){
        return userController.getUserById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login/{username}:{password}")
    public User authenticateUser(@PathParam("username") String username, @PathParam("password") String password){
        return userController.authenticateUser(username, password);
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/anyuser?")
    public boolean anyUserExists(){
        return userController.anyUserExists();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/last")
    public int getLastUserId(){
        return userController.getLastUserId();
    }

    @POST
    @Path("/adduser/{username}:{password}")
    public void registerUser(@PathParam("username") String username, @PathParam("password") String password, int auth){
        userController.registerUser(username,password, auth);
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/check/{username}:{password}")
    public Boolean checkUserExistence(@PathParam("username") String username, @PathParam("password") String password){
        return userController.checkUserExistence(username, password);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/register/{username}:{password}:{auth}")
    public Response createRegisterResponse(@PathParam("username") String username, @PathParam("password") String password,
                                           @PathParam("auth") int auth){
        System.out.println("USER AANMAKEN: " + username + password + auth);
        return userController.createRegisterResponse(username,password, auth);
    }

}
