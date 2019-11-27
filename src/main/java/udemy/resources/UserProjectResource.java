package udemy.resources;

import udemy.core.models.Project;
import udemy.core.models.User;
import udemy.persistance.UserProjectDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/favourited")
public class UserProjectResource {

    private UserProjectDAO userProjectDAO;

    public UserProjectResource(UserProjectDAO userProjectDAO) {
        this.userProjectDAO = userProjectDAO;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/save/{projectId}:{userId}")
    public Response favouriteProject(@PathParam("projectId") int projectId, @PathParam("userId")int userId){
        if (!alreadyFavourited(userId,projectId)) {
            userProjectDAO.favouriteProject(projectId, userId);
            increaseLikes(projectId);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/remove/{projectId}:{userId}")
    public Response unfavouriteProject(@PathParam("projectId") int projectId, @PathParam("userId") int userId){
        if (alreadyFavourited(userId,projectId)){
            userProjectDAO.unfavouriteProject(projectId,userId);
            decreaseLikes(projectId);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/exists?/{userId}:{projectId}")
    public boolean alreadyFavourited(@PathParam("userId")int userId,@PathParam("projectId")int projectId){
        System.out.println("already exists? "+userProjectDAO.alreadyFavourited(userId,projectId));
        return userProjectDAO.alreadyFavourited(userId,projectId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/show/{userId}")
    public List<Project> getFavouritedProjects(@PathParam("userId") int userId){
        List<Project> projects = userProjectDAO.getFavouritedProjects(userId);
        return projects;
    }

    public void increaseLikes(int projectId){
        userProjectDAO.increaseLikes(projectId);
    }

    public void decreaseLikes(int projectId){
        userProjectDAO.decreaseLikes(projectId);
    }




    //VOOR TESTEN GEBRUIKT - NIET HERGEBRUIKEN
    @POST
    @Path("/save/test/{projectId}")
    public void testFavouriteProject(@PathParam("projectId")int projectId){
        User user = new User(3,"jaime","versluis",true,false, false);
        favouriteProject(projectId,1);

    }

    @POST
    @Path("/remove/test/{projectId}")
    public void testUnfavouriteProject(@PathParam("projectId") int projectId){
        User user = new User(3,"jaime","versluis",true,false, false);
        unfavouriteProject(projectId,1);
    }

}
