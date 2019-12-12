package udemy.Controllers;

import udemy.core.models.Project;
import udemy.persistance.UserProjectDAO;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The controller class for the  projects of users based requests
 * This class uses the data from the UserProjectResource layer, applies some logic
 * and requests the UserProjectDAO for data accordingly.
 */
public class UserProjectController {


    private UserProjectDAO userProjectDAO;

    public UserProjectController(UserProjectDAO userProjectDAO){
        this.userProjectDAO = userProjectDAO;
    }

    /**
     * In order to like or favourite a project it is checked if the project is already favourited
     * The like amount of the project is also increased
     * @param projectId
     * @param userId
     * @return
     */
    public Response favouriteProject(int projectId, int userId){
        if (!alreadyFavourited(userId,projectId)) {
            userProjectDAO.favouriteProject(projectId, userId);
            increaseLikes(projectId);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * The opposite of the favouriteProject method
     * @param projectId
     * @param userId
     * @return
     */
    public Response unfavouriteProject(int projectId, int userId){
        if (alreadyFavourited(userId,projectId)){
            userProjectDAO.unfavouriteProject(projectId,userId);
            decreaseLikes(projectId);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public boolean alreadyFavourited(int userId,int projectId){
        return userProjectDAO.alreadyFavourited(userId,projectId);
    }

    public void increaseLikes(int projectId){
        userProjectDAO.increaseLikes(projectId);
    }

    public void decreaseLikes(int projectId){
        userProjectDAO.decreaseLikes(projectId);
    }

    public List<Project> getFavouritedProjects( int userId){
        return userProjectDAO.getFavouritedProjects(userId);
    }



}
