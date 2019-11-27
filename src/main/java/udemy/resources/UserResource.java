package udemy.resources;

import udemy.core.models.User;
import udemy.persistance.UserDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//localhost:8080/path

@Path("/user")
public class UserResource {

    private UserDAO userDAO;

    public UserResource(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public List<User> getAllUsers(){
        List<User> users = userDAO.getAllUsers();
        System.out.println("Get request for all users");
        return users;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public User getUserById(@PathParam("id") int id){
        User user = userDAO.getUserById(id);
        System.out.println("user details: " + user.getUsername() + user.getPassword() + user.getId());
        System.out.println("Get request for user: " + id);
        return user;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login/{username}:{password}")
    public User authenticateUser(@PathParam("username") String username, @PathParam("password") String password){
        User user = userDAO.getUserByLogin(username, password);
        System.out.println("Get request for user existence: "+username+", "+password);
        return user;
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/anyuser?")
    public boolean anyUserExists(){
        return userDAO.anyUserExists();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/last")
    public int getLastUserId(){
        int lastUserId = 0;
        if(anyUserExists()){
            lastUserId = userDAO.getLastUserId();
        }
        System.out.println("last user id is: " + lastUserId);
        return lastUserId;
    }

    @POST
    @Path("/adduser/{username}:{password}")
    public void registerUser(@PathParam("username") String username, @PathParam("password") String password){
        int newUserId = getLastUserId() + 1;
        userDAO.registerUser(newUserId,username,password,true,false);
        System.out.println("New user added");
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/check/{username}")
    public Boolean checkUserExistence(@PathParam("username") String username){
        System.out.println("checking for user existence: "+username);
        return userDAO.checkUserExistence(username);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/register/{username}:{password}")
    public Response createRegisterResponse(@PathParam("username") String username, @PathParam("password") String password){
        System.out.println("user " + username + "exists: " + checkUserExistence(username));
        if(!checkUserExistence(username)){
            registerUser(username,password);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

}
