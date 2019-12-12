package udemy.resources;

import udemy.Controllers.ProjectController;
import udemy.core.models.Project;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Resource class for projects
 * General path is defined as /projects
 * and subpaths are related to the methods
 * With pathparams certain simple values can be passed
 * and the @consume can be used to receive objects in json format
 * @produces is used to create the aforementioned json
 */
@Path("/projects")
public class ProjectResource {

    private ProjectController projectController;



    public ProjectResource(ProjectController projectController) {
        this.projectController = projectController;
    }

    @GET
    @Path("/all/{pageNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjectsByPage(@PathParam("pageNumber") int pageNumber){
        return projectController.getProjectsByPage(pageNumber);
    }


    @GET
    @Path("/projectId={projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getProjectById(@PathParam("projectId")int projectId){
        return projectController.getProjectById(projectId);
    }


    @GET
    @Path("/any")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean anyProjectsExist(){
        return projectController.anyProjectsExist();
    }

    @GET
    @Path("/last")
    @Produces(MediaType.TEXT_PLAIN)
    public int getLastProjectId(){
        return projectController.getLastProjectId();
    }


    /**
     * Here is an octet stream used for the binary data of the pdf.
     * This can be read on the frontend as an inputstream and read to a file
     * @param id
     * @return
     */
    @GET
    @Path("/{id}/pdf")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(@PathParam("id") int id) {
       return projectController.getFile(id);
    }


    @GET
    @Path("/favourited/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getFavProjects(@PathParam("userid") int userid){
        return projectController.getFavProjects(userid);
    }


    /**
     * The .* sign used in the pathparams indicate that this field is optional and can be left blank if desired
     * This allows the frontend to send a varying amount of filter options but not required to send all
     * @param title
     * @param author
     * @param category
     * @param study
     * @param course
     * @return
     */
    @GET
    @Path("/filter/{title: .*}+{author: .*}+{category: .*}+{study: .*}+{course: .*}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjectFile(@PathParam("title") String title, @PathParam("author") String author, @PathParam("category") String category, @PathParam("study") String study, @PathParam("course") String course){
        return projectController.getProjectFile(title, author, category, study, course);
    }

    @GET
    @Path("/filter/{title: .*}+{author: .*}+{category: .*}+{study: .*}+{course: .*}/count")
    @Produces(MediaType.APPLICATION_JSON)
    public int getProjectsCount(@PathParam("title") String title, @PathParam("author") String author, @PathParam("category") String category, @PathParam("study") String study, @PathParam("course") String course){
        return projectController.getProjectsCount(title, author, category, study, course);
    }

    @GET
    @Path("/filter/{title: .*}+{author: .*}+{category: .*}+{study: .*}+{course: .*}/{pageNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjectsByPageWithFilters(@PathParam("pageNumber") int pageNumber, @PathParam("title") String title,
                                                      @PathParam("author") String author, @PathParam("category") String category,
                                                      @PathParam("study") String study, @PathParam("course") String course){
        return projectController.getProjectsByPageWithFilters(pageNumber, title, author, category,study, course);
    }

    @GET
    @Path("/category/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCategories(){
        return projectController.getCategories();
    }

    @GET
    @Path("/study/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getStudy(){
        return projectController.getStudy();
    }

    @GET
    @Path("/course/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCourse(){
        return projectController.getCourse();
    }

    @GET
    @Path("/top")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getTopProjects(){
        return projectController.getTopProjects();
    }

    @GET
    @Path("/topLiked")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getTopLikedProject() {
        return projectController.getTopProjects();
    }

}