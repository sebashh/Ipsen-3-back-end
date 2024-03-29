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
import java.util.Date;
import java.util.List;
import java.util.logging.Filter;
import java.util.Optional;

@Path("/projects")
public class ProjectResource {
    private ProjectController projectController;

    public ProjectResource(ProjectController projectController) {
        this.projectController= projectController;
    }




    @POST
    @Path("/project/create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createProject(Project project, @Auth Optional<AuthUser> user){
        if(project.clientId != 0){
            projectController.uploadProject(project);
        } else {
            projectController.uploadProject(project, Integer.parseInt(user.get().getName()));
        }
        return Response
                .status(200)
                .build();
    }

//    @POST
//    @Path("/admin/project/create")
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response createProjectByAdmin(Project project, int id){
//        projectController.uploadProject(project, id);
//        return Response
//                .status(200)
//                .build();
//    }


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
    @Path("/projectsNewerThan={newerThan}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectsNewerThan(@PathParam("newerThan") String newerThan) {
        List<Project> ProjectsNewerThan = projectController.getProjectsNewerThan(newerThan);
        return Response
                .status(200)
                .entity(ProjectsNewerThan)
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
    @Path("/filter/study={studyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjectsByStudyId(@PathParam("studyId") int studyId) {
        List<Project> ProjectsByStudy = projectController.getAllProjectsByStudyId(studyId);
        return Response
                .status(200)
                .entity(ProjectsByStudy)
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
    @Path("/filter/study={studyId}+category={categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectsFromStudyAndCategoryId(@PathParam("studyId") int studyId, @PathParam("categoryId") int categoryId ) {
        List<Project> ProjectsByBoth = projectController.getProjectsFromStudyAndCategoryId(studyId, categoryId);
        return Response
                .status(200)
                .entity(ProjectsByBoth)
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
    @Path("/filter/category={categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjectsByCategoryId(@PathParam("categoryId") int categoryId){

        List<Project> ProjectsByCategory = projectController.getAllProjectsByCategoryId(categoryId);
        return Response
                .status(200)
                .entity(ProjectsByCategory)
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
    @GET
    @Path("/projects=all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProjects(){

        List<Project> AllProjects = projectController.getAllProjects();
        return Response
                .status(200)
                .entity(AllProjects)
                .build();
    }

    @DELETE
    @Path("/delete={id}")
    public void delete(@PathParam("id") int id) {
        projectController.deleteProject(id);
    }

    @PUT
    @Path("/projectUpdate")
    public void updateTeacher(Project project){
        projectController.updateProject(project);
    }


    @GET
    @Path("/project={id}/view")
    @Produces(MediaType.APPLICATION_JSON)
    public Response increaseProjectViews(@PathParam("id") int projectId){
        System.out.println("project is bekeken: " + projectId);
        projectController.increaseProjectViews(projectId);
        return Response
                .status(200)
                .build();
    }
}
