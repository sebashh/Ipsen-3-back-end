package udemy.resources;


import udemy.Controllers.ProjectController;
import udemy.core.models.Project;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ipsen3projects")
public class ProjectResource {
    private ProjectController projectController;

    public ProjectResource(ProjectController projectController) {
        this.projectController= projectController;
    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTest(){
        String output = "hello world uwu" ;
        return Response
                .status(200)
                .entity(output)
                .build();
    }

    @POST
    @Path("/upload/test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postTest(Project project){
        projectController.uploadProject(project);
        return Response.status(200).build();
    }

}
