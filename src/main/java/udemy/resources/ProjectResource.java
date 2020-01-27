package udemy.resources;


import io.dropwizard.auth.Auth;
import udemy.Controllers.ProjectController;
import udemy.auth.AuthUser;
import udemy.core.models.Project;
import udemy.core.models.User;

import javax.annotation.security.RolesAllowed;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/ipsen3projects")
public class ProjectResource {
    private ProjectController projectController;

    public ProjectResource(ProjectController projectController) {
        this.projectController= projectController;
    }




    @POST
    @Path("/project/create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTest(Project project, @Auth Optional<AuthUser> user){
        projectController.uploadProject(project, Integer.parseInt(user.get().getName()));
        return Response
                .status(200)
                .build();
    }

    @GET
    @RolesAllowed({"teacher", "student"})
    @Path("/project={id}/follow")
    @Produces(MediaType.APPLICATION_JSON)
    public Response followProject(@PathParam("id") int projectId, @Auth Optional<AuthUser> user) {
        projectController.followProject(projectId, Integer.parseInt(user.get().getName()));
        return Response
                .status(200)
                .build();
    }

    @GET
    @RolesAllowed({"teacher", "student"})
    @Path("/project={id}/isFollowing")
    public Response isFollowingProject(@PathParam("id") int projectId, @Auth Optional<AuthUser> user){
        boolean following = projectController.isFollowing(projectId, Integer.parseInt(user.get().getName()));
        return Response
                .status(200)
                .entity(following)
                .build();
    }

    @GET
    @RolesAllowed({"teacher", "student", "client", "admin"})
    @Path("/projects/all")
    public Response getProject(){
        return Response
                .status(200)
                .entity(projectController.getAllProjects())
                .build();
    }

    @GET
    @RolesAllowed({"teacher", "student"})
    @Path("/project={id}/unfollow")
    @Produces(MediaType.APPLICATION_JSON)
    public Response unFollowProject(@PathParam("id") int projectId, @Auth Optional<AuthUser> user) {
        projectController.unFollowProject(projectId, Integer.parseInt(user.get().getName()));
        return Response
                .status(200)
                .build();
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
    @Path("/followed/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetFollowedProjectsOfUser(@Auth Optional<AuthUser> user){
        int userId = Integer.parseInt(user.get().getName());
        List<Project> followedUserProjects = projectController.getUserProjects(userId);
        return Response
                .status(200)
                .entity(followedUserProjects)
                .build();
    }

    @GET
    @Path("/interested/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreatedProjectsWithInterests(@Auth Optional<AuthUser> user){
        int userId = Integer.parseInt(user.get().getName());
        List<Project> userInterestedProjects = projectController.getCreatedProjectWithInterest(userId);
        return Response
                .status(200)
                .entity(userInterestedProjects)
                .build();
    }

    @GET
    @Path("/clientProjects/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecentlyUpdatedProjects(@Auth Optional<AuthUser> user){
        int userId = Integer.parseInt(user.get().getName());
        List<Project> recentlyUpdatedProjects = projectController.getRecentlyUpdatedProjects(userId);
        return Response
                .status(200)
                .entity(recentlyUpdatedProjects)
                .build();
    }

    @GET
    @Path("/clientProjects/top/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopViewedClientProjects(@Auth Optional<AuthUser> user){
        int userId = Integer.parseInt(user.get().getName());
        List<Project> topViewedClientProjects = projectController.getTopViewedClientProjects(userId);
        return Response
                .status(200)
                .entity(topViewedClientProjects)
                .build();
    }


    @GET
    @Path("/project={id}/follow/amount")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFollowAmount(@PathParam("id") int id){
        int amount = projectController.getFollowAmount(id);
        return Response
                .status(200)
                .entity(amount)
                .build();
    }

    @GET
    @RolesAllowed("teacher")
    @Path("/project={id}/access/request")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestAccess(@PathParam("id") int id, @Auth Optional<AuthUser> userId){
        projectController.requestAccess(id, Integer.parseInt(userId.get().getName()));
        return Response
                .status(200)
                .build();
    }

    @GET
    @RolesAllowed("client")
    @Path("/project={id}/access/teacher-id={userId}/response={accepted}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response accessRequestResponse(@PathParam("accepted")boolean accepted, @PathParam("userId") int teacherId, @PathParam("id") int id, @Auth Optional<AuthUser> userId){
        projectController.accessRequestResponse(accepted, Integer.parseInt(userId.get().getName()), teacherId, id);
        return Response
                .status(200)
                .build();
    }

    @GET
    @RolesAllowed("teacher")
    @Path("/project={id}/access/information")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAccessInformation(@PathParam("id") int id, @Auth Optional<AuthUser> userId){
        String info = projectController.getAccessInformation(id, Integer.parseInt(userId.get().getName()));
        return Response
                .status(200)
                .entity(info)
                .build();
    }

    @GET
    @RolesAllowed("client")
    @Path("/project={id}/access/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccessMembers(@PathParam("id") int projectId){
        return Response
                .status(200)
                .entity(projectController.getAllAccessMembers(projectId))
                .build();
    }

    @GET
    @RolesAllowed("client")
    @Path("/project={id}/access/requests/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccessRequests(@PathParam("id") int projectId){
        return Response
                .status(200)
                .entity(projectController.getAllRequests(projectId))
                .build();
    }
}
