package udemy.resources;

import udemy.Controllers.UploadController;
import udemy.core.models.Project;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Resource class for uploading projects
 * General path is defined as /upload
 * and subpaths are related to the methods
 * With pathparams certain simple values can be passed
 * and the @consume can be used to receive objects in json format
 * @produces is used to create the aforementioned json
 */
@Path("/upload")
public class UploadResource {

    private UploadController uploadController;


    public UploadResource(UploadController uploadController) {
        this.uploadController = uploadController;
    }



    @GET
    @Path("/any")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean anyProjectsExist(){
        return uploadController.anyProjectsExist();
    }

    @GET
    @Path("/last")
    @Produces(MediaType.TEXT_PLAIN)
    public int getLastProjectId(){
        return uploadController.getLastProjectId();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/upload")
    public Response uploadProject(Project project){
        return uploadController.uploadProject(project);
    }


    @GET
    @Path("/by/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getProjectsUploadedBy(@PathParam("userId") int userId){
        System.out.println("user id is : " + userId);
        return uploadController.getProjectsUploadedBy(userId);
    }

    @POST
    @Path("/delete/{projectId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteProject(@PathParam("projectId") int projectId){
        return uploadController.deleteProject(projectId);
    }
}

