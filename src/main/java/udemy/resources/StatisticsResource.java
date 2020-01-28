package udemy.resources;

import io.dropwizard.auth.Auth;
import udemy.Controllers.StatisticsController;
import udemy.ENUM.Roles;
import udemy.auth.AuthUser;
import udemy.core.models.DateStatistic;
import udemy.core.models.Project;
import udemy.core.models.Statistics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Path("/statistics")
public class StatisticsResource {

    private StatisticsController statisticsController;

    public StatisticsResource(StatisticsController statisticsController){
        this.statisticsController = statisticsController;
    }

    @GET
    @Path("/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public Statistics getStatistics(){
        return statisticsController.getAllStatistics();
    }

    @GET
    @Path("/topprojects")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Project> getTopPopularProjects(){
        return statisticsController.getTopPopularProjects();
    }


    @GET
    @Path("/student")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecentUserStatistics(@Auth Optional<AuthUser> user){
        int userId = Integer.parseInt(user.get().getName());
        ArrayList<Integer> recentStatistics = statisticsController.getRecentUserStatistics(userId);
        return Response
                .status(200)
                .entity(recentStatistics)
                .build();
    }

    @GET
    @Path("/teacher")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecentTeacherStatistics(@Auth Optional<AuthUser> user){
        int userId = Integer.parseInt(user.get().getName());
        ArrayList<Integer> recentStatistics = statisticsController.getRecentTeacherStatistics(userId);
        return Response
                .status(200)
                .entity(recentStatistics)
                .build();
    }

    @GET
    @Path("/client")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecentClientStatistics(@Auth Optional<AuthUser> user){
        int userId = Integer.parseInt(user.get().getName());
        ArrayList<Integer> recentStatistics = statisticsController.getRecentClientStatistics(userId);
        return Response
                .status(200)
                .entity(recentStatistics)
                .build();
    }

    @GET
    @Path("/admin/project")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DateStatistic> getAdminProjectStatistics(){
        return statisticsController.getAdminProjectStatistics();
    }

    @GET
    @Path("/admin/paper")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DateStatistic> getAdminPaperStatistics(){
        return statisticsController.getAdminPaperStatistics();
    }

    @GET
    @Path("/admin/views")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DateStatistic> getAdminViewStatistics(){
        return statisticsController.getAdminViewStatistics();
    }

    @GET
    @Path("/admin/login/{userType}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DateStatistic> getAdminLoginStatistics(@PathParam("userType")String userType){
        return statisticsController.getAdminLoginStatistics(userType);
    }

}
