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
        System.out.println(id);
        return projectDAO.getProject(id);
    }

    public List<Project> getAllProjectsOfClient(int client_id){
        return projectDAO.getAllProjectsOfClient(client_id);
    }

    public List<Project> getUserProjects(int id) {
        return projectDAO.getUserFollowedProjects(id);
    }
}
