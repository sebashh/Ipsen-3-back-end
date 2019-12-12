package udemy.resources;

import udemy.Controllers.StatisticController;
import udemy.core.models.Project;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;

/**
 * Resource class for statistics
 * General path is defined as /statistic
 * and subpaths are related to the methods
 * With pathparams certain simple values can be passed
 * and the @consume can be used to receive objects in json format
 * @produces is used to create the aforementioned json
 */
@Path("/statistic")
public class StatisticResource {

    private StatisticController statisticController;

    public StatisticResource (StatisticController statisticController){
        this.statisticController = statisticController;

    }

    @GET
    @Path("/topviewed")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getTopViewedProjects(){
        return  statisticController.getTopViewedProjects();
    }

    @GET
    @Path("/topcategories")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String,Integer> getTopLikedCategories(){
        return statisticController.getTopLikedCategories();
    }

    @GET
    @Path("/viewsdate/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getViewsOnDate(@PathParam("date") String date){
        date = date.replace("-","");
        return statisticController.getViewsOnDate(date);
    }

    @GET
    @Path("/viewsdateproject/{date}:{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getViewsOnDateFromProject(@PathParam("date") String date, @PathParam("projectId")int projectId){
        date = date.replace("-","");
        return statisticController.getViewsOnDateFromProject(date,projectId);
    }

    @GET
    @Path("/viewsmonthyear/{month}:{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getViewsDuringMonth(@PathParam("month") String month, @PathParam("year") int year){
        return statisticController.getViewsDuringMonth(month, year);
    }


    @GET
    @Path("/viewsmonthyearproject/{month}:{year}:{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getViewsDuringMonthFromProject(@PathParam("month") String month, @PathParam("year") int year, @PathParam("projectId") int projectId){
        return statisticController.getViewsDuringMonthFromProject(month, year, projectId);
    }

}
