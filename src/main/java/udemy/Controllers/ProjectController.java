package udemy.Controllers;

import udemy.core.models.Project;
import udemy.core.models.Project2;
import udemy.persistance.ProjectDAO;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * The controller class for the general project based requests
 * This class uses the data from the projectResource layer, applies some logic
 * and requests the projectDAO for data accordingly.
 */

public class ProjectController {

    private ProjectDAO projectDAO;
    private int amount = 20;

    public ProjectController(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    /**
     * Method that returns a new starting index based on the page nummber provided
     * @param pageNumber
     * @return
     */
    public int getStartingIndex(int pageNumber){
        return Math.max(0,(pageNumber-1)*amount);
    }

    /**
     * Uses a starting index based on project id's per page a list will be constructed with the correct projects
     * @param pageNumber
     * @return
     */
    public List<Project> getProjectsByPage(int pageNumber){
        int startingIndex = getStartingIndex(pageNumber);
        List<Project> projects = projectDAO.getProjectByPage(amount,startingIndex);
        return projects;
    }

    public Project getProjectById(int projectId){
        Project project = projectDAO.getProjectById(projectId);
        return project;
    }

    public boolean anyProjectsExist(){
        return projectDAO.anyProjectsExist();
    }

    /**
     * Get the highest project id from the database for further use
     * @return
     */
    public int getLastProjectId(){
        int lastProjectId = 0;
        if(anyProjectsExist()){
            lastProjectId = projectDAO.getLastProjectId();
        }
        return lastProjectId;
    }

    /**
     * Creates a project with a project file (byte array) and returns a response
     * Furthermore this class is called when a project is selected in the front end for further viewing
     * and as such the amount of views is incremented and the viewdate is registered
     * @param id
     * @return
     */
    public Response getFile(int id) {
        Project2 project = projectDAO.getProjectFileById(id);
        byte[] bytes = project.getPdfFile();
        projectViewed(id);
        projectViewedAtDate(id,project.getUploadDate());
        return Response.ok(new ByteArrayInputStream(bytes), MediaType.APPLICATION_OCTET_STREAM).build();
    }

    private void projectViewed(int id) {
        projectDAO.projectViewed(id);
    }

    private void projectViewedAtDate(int id, String date){
        date = date.replace("-","");
        projectDAO.projectViewedAtDate(id,date);}

    public List<Project> getFavProjects(int userid){
        return projectDAO.getFavouritedProjects(userid);
    }

    public List<Project> getProjectFile(String title, String author, String category,  String study, String course){
        return projectDAO.getProjectFile(title, author, category, study, course);
    }

    public int getProjectsCount(String title,String author, String category, String study, String course){
        return projectDAO.getProjectsAmount(title, author, category, study, course);
    }

    /**
     * Multiple possible search filter options are given, which can be either a value or null
     * the filters are corrected for database use
     * @param pageNumber
     * @param title
     * @param author
     * @param category
     * @param study
     * @param course
     * @return
     */
    public List<Project> getProjectsByPageWithFilters(int pageNumber, String title, String author, String category, String study,String course){
        int startingIndex = getStartingIndex(pageNumber);
        title = title.replace("_", " ");
        author = author.replace("_", " ");
        category = category.replace("_", " ");
        study = study.replace("_", " ");
        course = course.replace("_", " ");
        return projectDAO.getProjectsByPageWithFilters(startingIndex, amount, title, author, category,study, course);
    }

    public List<String> getCategories(){
        return projectDAO.getCategories();
    }

    public List<String> getStudy(){
        return projectDAO.getStudies();
    }

    public List<String> getCourse(){
        return projectDAO.getCourses();
    }

    public List<Project> getTopProjects(){
        return projectDAO.getTopProjects();
    }


}
