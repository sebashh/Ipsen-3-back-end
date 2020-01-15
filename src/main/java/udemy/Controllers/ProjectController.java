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

        int study_id = projectDAO.getStudyId(project.study);
        int category_id = projectDAO.getCategoryId(project.category);
        int project_id = projectDAO.getNewProjectId() + 1;
        projectDAO.uploadProject(project_id, project.title, project.description, project.clientId, study_id, category_id);

    }

    public List<Project> getUserProjects(int id) {
        return projectDAO.getUserFollowedProjects(id);
    }
}
