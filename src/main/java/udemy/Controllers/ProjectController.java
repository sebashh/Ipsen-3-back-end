package udemy.Controllers;

import udemy.core.models.Project;
import udemy.persistance.ProjectDAO;

import java.util.List;


public class ProjectController {
    private ProjectDAO projectDAO;

    public ProjectController(ProjectDAO projectDAO) {
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
}
