package udemy.resources;

import udemy.Controllers.UserController;
import udemy.core.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {
    private UserController userController;

    public UserResource(UserController userController) {
        this.userController = userController;
    }

    @POST
    @Path("/register/client")
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
    @Path("/register/student")
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
    @Path("/register/teacher")
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

    @DELETE
    @Path("/delete={id}")
    public void delete(@PathParam("id") int id) {
        userController.deleteUser(id);
    }


}
