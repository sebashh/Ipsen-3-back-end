package udemy.Controllers;

import udemy.core.models.Project;
import udemy.persistance.StatisticDAO;

import java.util.HashMap;
import java.util.List;

/**
 * The controller class for the specific statistic requests
 * This class uses the data from the statisticResource layer, applies some logic
 * and requests the statisticDAO for data accordingly.
 */
public class StatisticController {

    private StatisticDAO statisticDAO;

    public StatisticController(StatisticDAO statisticDAO) {
        this.statisticDAO = statisticDAO;
    }

    public List<Project> getTopViewedProjects(){
        return  statisticDAO.getTopViewedProjects();
    }

    public HashMap<String, Integer> getTopLikedCategories() {
        return statisticDAO.getTopLikedCategories();
    }

    public int getViewsOnDate(String date){
        return statisticDAO.getViewsOnDate(date);
    }

    public int getViewsOnDateFromProject(String date, int projectId){
        return statisticDAO.getViewsOnDateFromProject(date,projectId);
    }

    /**
     * The database requires a certain style of requests
     * as such the data provied by the resource is restructured for database use
     * @param month
     * @param year
     * @return
     */
    public Integer getViewsDuringMonth(String month, int year) {
        if (Integer.valueOf(month) < 10){month = 0 + month;}
        return statisticDAO.getViewsDuringMonth(year+month+"__");
    }

    /**
     * The database requires a certain style of requests
     * as such the data provied by the resource is restructured for database use
     * This function is used to acquire data for specific projects
     * @param month
     * @param year
     * @return
     */
    public Integer getViewsDuringMonthFromProject(String month, int year, int projectId) {
        if (Integer.valueOf(month) < 10){month = 0 + month;}
        return statisticDAO.getViewsDuringMonthFromProject(year+month+"__", projectId);
    }
}
