package udemy.resources;


import udemy.Controllers.ProjectController;
import udemy.core.models.Project;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    @Path("/followed/user={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetFollowedProjectsOfUser(@PathParam("id") int id){
        List<Project> followedUserProjects = projectController.getUserProjects(id);
        return Response
                .status(200)
                .entity(followedUserProjects)
                .build();
    }

    @GET
    @Path("/interested/user={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreatedProjectsWithInterests(@PathParam("id") int id){
        List<Project> userInterestedProjects = projectController.getCreatedProjectWithInterest(id);
        return Response
                .status(200)
                .entity(userInterestedProjects)
                .build();
    }

    @GET
    @Path("/clientProjects/user={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecentlyUpdatedProjects(@PathParam("id") int id){
        List<Project> recentlyUpdatedProjects = projectController.getRecentlyUpdatedProjects(id);
        return Response
                .status(200)
                .entity(recentlyUpdatedProjects)
                .build();
    }

    @GET
    @Path("/clientProjects/top/user={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopViewedClientProjects(@PathParam("id") int id){
        List<Project> topViewedClientProjects = projectController.getTopViewedClientProjects(id);
        return Response
                .status(200)
                .entity(topViewedClientProjects)
                .build();
    }

}
