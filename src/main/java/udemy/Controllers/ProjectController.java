package udemy.Controllers;

import udemy.core.models.Project;
import udemy.persistance.ProjectDAO;

import java.sql.SQLOutput;
import java.util.List;


public class ProjectController {
    private ProjectDAO projectDAO;

    public ProjectController(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }


    public Project getProject(int id){
        System.out.println(id);
        return projectDAO.getProject(id);
    }

    public List<Project> getAllProjectsOfClient(int client_id){
        return projectDAO.getAllProjectsOfClient(client_id);
    }

    public List<Project> getAllProjects(){
        return projectDAO.getAllProjects();
    }

    public List<Project> getUserProjects(int id) {
        return projectDAO.getUserFollowedProjects(id);
    }

    public void deleteProject(int id){
        projectDAO.deleteProject(id);
    }

    public void updateProject(Project project) {
        System.out.println(project.title);
    }
}
