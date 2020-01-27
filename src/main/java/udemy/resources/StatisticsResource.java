package udemy.resources;

import udemy.Controllers.StatisticsController;
import udemy.ENUM.Roles;
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
    @Path("/student={studentName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecentUserStatistics(@PathParam("studentName") String studentName){
        ArrayList<Integer> recentStatistics = statisticsController.getRecentUserStatistics(studentName);
        return Response
                .status(200)
                .entity(recentStatistics)
                .build();
    }

    @GET
    @Path("/teacher={teacherName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecentTeacherStatistics(@PathParam("teacherName") String teacherName){
        ArrayList<Integer> recentStatistics = statisticsController.getRecentTeacherStatistics(teacherName);
        return Response
                .status(200)
                .entity(recentStatistics)
                .build();
    }

    @GET
    @Path("/client={clientName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecentClientStatistics(@PathParam("clientName") String clientName){
        ArrayList<Integer> recentStatistics = statisticsController.getRecentClientStatistics(clientName);
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
