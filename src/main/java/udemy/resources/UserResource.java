package udemy.resources;


import udemy.Controllers.UserController;
import udemy.core.models.Notification;
import udemy.core.models.Project;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user={id}")
public class UserResource {


    private UserController controller;

    public UserResource(UserController con){
        this.controller = con;
    }

    @GET
    @Path("/notifications")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotifications(@PathParam("id") int id){
        List<Project> notifications =  controller.getNotifications(id);
        return Response
                .status(200)
                .entity(notifications)
                .build();
    }

    @DELETE
    @Path("/delete")
    public void delete(@PathParam("id") int id) {
        controller.deleteUser(id);
    }


}
