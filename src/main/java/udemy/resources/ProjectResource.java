package udemy.resources;


import udemy.Controllers.ProjectController;
import udemy.core.models.Project;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.logging.Filter;

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
    public Response postTest(Project project) {
        projectController.uploadProject(project);
        return Response.status(200).build();
    }

    @GET
    @Path("/project={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProject(@PathParam("id") int id)
    {
        Project project = projectController.getProject(id);
        return Response
                .status(200)
                .entity(project)
                .build();
    }

    @GET
    @Path("/projects={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjectsOfClient(@PathParam("id") int id){

        List<Project> ProjectsOfClient = projectController.getAllProjectsOfClient(id);
        return Response
                .status(200)
                .entity(ProjectsOfClient)
                .build();
    }

    @GET
    @Path("/projectsNewerThan={newerThan}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectsNewerThan(@PathParam("newerThan") String newerThan)
    {
        List<Project> ProjectsNewerThan = projectController.getProjectsNewerThan(newerThan);
        return Response
                .status(200)
                .entity(ProjectsNewerThan)
                .build();
    }

    @GET
    @Path("/filter/study={studyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjectsByStudyId(@PathParam("studyId") int studyId){
        List<Project> ProjectsByStudy = projectController.getAllProjectsByStudyId(studyId);
        return Response
                .status(200)
                .entity(ProjectsByStudy)
                .build();
    }

    @GET
    @Path("/filter/study={studyId}+category={categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectsFromStudyAndCategoryId(@PathParam("studyId") int studyId, @PathParam("categoryId") int categoryId ){
        List<Project> ProjectsByBoth= projectController.getProjectsFromStudyAndCategoryId(studyId, categoryId);
        return Response
                .status(200)
                .entity(ProjectsByBoth)
                .build();
    }

    @GET
    @Path("/filter/category={categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjectsByCategoryId(@PathParam("categoryId") int categoryId){

        List<Project> ProjectsByCategory = projectController.getAllProjectsByCategoryId(categoryId);
        return Response
                .status(200)
                .entity(ProjectsByCategory)
                .build();
    }

}
