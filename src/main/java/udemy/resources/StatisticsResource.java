package udemy.resources;

import udemy.Controllers.StatisticsController;
import udemy.core.models.Project;
import udemy.core.models.Statistics;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

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
}
