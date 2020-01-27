package udemy.Controllers;

import udemy.User;
import udemy.core.models.AccessModel;
import udemy.core.models.Project;
import udemy.persistance.ProjectDAO;

import java.util.ArrayList;
import java.util.List;


public class ProjectController {
    private ProjectDAO projectDAO;
    private UserController userController;

    public ProjectController(ProjectDAO projectDAO, UserController userController) {
        this.userController = userController;
        this.projectDAO = projectDAO;
    }

    public void uploadProject(Project project) {

        projectDAO.uploadProject(1, project.title, project.description, project.clientId, 1, 1);

    }

    public Project getProject(int id){
        return projectDAO.getProject(id);
    }

    public void followProject(int project_id, int user_id){
        projectDAO.followProject(project_id, user_id);
    }

    public void unFollowProject(int project_id, int user_id){
        projectDAO.unFollowProject(project_id, user_id);
    }


    public List<Project> getAllProjectsOfClient(int client_id){
        return projectDAO.getAllProjectsOfClient(client_id);
    }

    public List<Project> getUserProjects(int id) {
        return projectDAO.getUserFollowedProjectsRandom(id);
    }

    public List<Project> getCreatedProjectWithInterest(int id){
        return projectDAO.getCreatedProjectWithInterests(id);
    }

    public List<Project> getRecentlyUpdatedProjects(int id) {
        return projectDAO.getRecentlyUpdatedProjects(id);
    }

    public List<Project> getTopViewedClientProjects(int id) {
        return projectDAO.getTopViewedProjectsClient(id);
    }

    public int getFollowAmount(int id) {
        return projectDAO.getFollowAmount(id);
    }

    public boolean isFollowing(int projectId, int userId) {
        return projectDAO.isFollowing(projectId, userId);
    }

    public String getAccessInformation(int id, int userId) {
        if(projectDAO.isInAccessTable(id, userId)){
            if(projectDAO.hasAccess(id, userId)) return "access";
            else return "in-progress";
        }
        return "no-access";
    }

    public void requestAccess(int id, int userId) {
        projectDAO.requestAccess(id, userId);
    }

    public boolean isProjectOwner(int id, int userId) {
        return userId == projectDAO.getProjectOwner(id);

    }

    public boolean accessRequestResponse(boolean accepted, int teacherId, int userId, int projectId) {
        if(isProjectOwner(projectId, userId)){
            if(accepted) projectDAO.acceptAcces(projectId, teacherId);
            else projectDAO.denieAcces(projectId, teacherId);
            return true;
        }
        throw new SecurityException("Not the owner of the project");
    }

    public List<AccessModel> getAllAccessMembers(int projectId){
        List<AccessModel> members = new ArrayList<>();
        int[] teachers = projectDAO.getAllAccess(projectId);
        for (int teacher : teachers) {
            members.add(new AccessModel(userController.getUserEmail(teacher), teacher));
        }
        return members;
    }

    public List<AccessModel> getAllRequests(int projectId) {
        List<AccessModel> members = new ArrayList<>();
        int[] teachers = projectDAO.getAllRequests(projectId);
        for (int teacher : teachers) {
            members.add(new AccessModel(userController.getUserEmail(teacher), teacher));
        }
        return members;
    }
}
