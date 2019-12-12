package udemy.resources;

import udemy.Controllers.UserProjectController;
import udemy.core.models.Project;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Resource class for favourited projects
 * General path is defined as /favourited
 * and subpaths are related to the methods
 * With pathparams certain simple values can be passed
 * and the @consume can be used to receive objects in json format
 * @produces is used to create the aforementioned json
 */
@Path("/favourited")
public class UserProjectResource {

    private UserProjectController userProjectController;

    public UserProjectResource(UserProjectController userProjectController) {
        this.userProjectController= userProjectController;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/save/{projectId}:{userId}")
    public Response favouriteProject(@PathParam("projectId") int projectId, @PathParam("userId")int userId){
        return userProjectController.favouriteProject(projectId,userId);
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/remove/{projectId}:{userId}")
    public Response unfavouriteProject(@PathParam("projectId") int projectId, @PathParam("userId") int userId){
        return userProjectController.unfavouriteProject(projectId,userId);
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/exists?/{userId}:{projectId}")
    public boolean alreadyFavourited(@PathParam("userId")int userId, @PathParam("projectId")int projectId){
        return userProjectController.alreadyFavourited(userId,projectId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/show/{userId}")
    public List<Project> getFavouritedProjects(@PathParam("userId") int userId){
        return userProjectController.getFavouritedProjects(userId);
    }


}
